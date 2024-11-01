package com.Familyship.checkkuleogi.domains.book.implementation;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.domain.repository.BookRepository;
import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.exception.BookException;
import com.Familyship.checkkuleogi.domains.book.exception.BookExceptionType;
import com.Familyship.checkkuleogi.domains.like.domain.BookLike;
import com.Familyship.checkkuleogi.domains.like.domain.repository.BookLikeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookCacheManager {
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper

    private static final String BOOK_CACHE_KEY_PREFIX = "book";
    private static final String RECENTLY_VIEWED_BOOKS_PREFIX = "recently-viewed-books:user";
    //public static final String BOOK_LIKE_CACHE_KEY_PREFIX = "book-like:";

    private final BookRepository bookRepository;
    private final BookLikeRepository bookLikeRepository;


    public Book findBookBy(Long bookIdx) {
        return bookRepository.findById(bookIdx)
                .orElseThrow(() -> new BookException(BookExceptionType.BOOK_NOT_FOUND_EXCEPTION));
    }

    public List<BookCachingItem> findBookListBy(Long childIdx) {
        String listKey = RECENTLY_VIEWED_BOOKS_PREFIX + ":" + childIdx;
        List<BookCachingItem> recentBooks = new ArrayList<>();

        List<String> recentBookIds = redisTemplate.opsForList().range(listKey, 0, -1);

        for (String bookIdx : recentBookIds) {
            // 2. 각 bookIdx에 대해 글로벌 캐시에서 메타데이터를 가져오거나, 없으면 DB 조회
            recentBooks.add(findBookFromCacheOrDB(childIdx, Long.valueOf(bookIdx)));
        }
        return recentBooks;
    }

    // 책 메타데이터 캐시에서 조회
    public BookCachingItem findBookFromCacheOrDB(Long childIdx, Long bookIdx) {
        String cacheBookKey = BOOK_CACHE_KEY_PREFIX + ":" + bookIdx;

        // 1. 글로벌 캐시에서 책 데이터를 먼저 조회
        String cachedBookJSon = redisTemplate.opsForValue().get(cacheBookKey);
        Boolean likeDislike = findLikeFromCacheOrDB(childIdx, bookIdx);

        if (cachedBookJSon == null) {
            // 2. 캐시에 없을 경우 DB에서 조회하여 캐시에 저장
            Book book = this.findBookBy(bookIdx); // DB 조회 메서드 호출

            BookCachingItem cachedBook = fromBookToBookCachingItem(book, likeDislike);
            cachedBookJSon = this.serializeBook(cachedBook);
            redisTemplate.opsForValue().set(cacheBookKey, cachedBookJSon, Duration.ofDays(7)); // 캐시에 저장

            return cachedBook;
        }
        BookCachingItem bookCachingItem = this.deserializeBookCachingItem(cachedBookJSon);
        // `isLike` 필드가 최신 상태로 반영되도록 새로운 객체 생성
        bookCachingItem = updateLikeData(bookCachingItem, likeDislike);
        return bookCachingItem;
    }

    public Boolean findLikeFromCacheOrDB(Long childIdx, Long bookIdx) {

        //TODO 추후, 좋아요까지 캐싱할 것인지 한다면 DB에서는 따로 관리안할지 고민필요. 좋아요는 쉽게 update되기 때문에, 현재 동기화 이슈가 있을 수 있음
        /*
        String likeKey = BOOK_LIKE_CACHE_KEY_PREFIX + childIdx + ":" + bookIdx;

        // 1. 글로벌 캐시에서 좋아요 데이터를 먼저 조회
        String likeStatus = redisTemplate.opsForValue().get(likeKey);

        if(likeStatus == null) {
            // 2. 캐시에 없을 경우 DB에서 조회하여 캐시에 저장*/
            Optional<BookLike> bookLike = bookLikeRepository.findByChildIdxAndBookIdx(childIdx, bookIdx);
            Boolean likeDislike = null;

            // 1 - 좋아요, 0 - 싫어요, null - 기록 없음
            if(bookLike.isPresent()) likeDislike = bookLike.get().getLikedislike();

            //this.cacheBookLikeStatus(childIdx, bookIdx, likeDislike);
            return likeDislike;
        /* }
        return Boolean.parseBoolean(likeStatus);*/
    }

    public void cacheRecentlyViewedBook(BookCachingItem bookCachingItem, Long childIdx) {
        String listKey = RECENTLY_VIEWED_BOOKS_PREFIX + ":" + childIdx;

        // 1. 리스트에 해당 책 ID가 이미 있는지 확인하고 중복 방지
        List<String> recentBookIds = redisTemplate.opsForList().range(listKey, 0, -1);
        String bookIdxStr = String.valueOf(bookCachingItem.idx());

        if (recentBookIds != null && recentBookIds.contains(bookIdxStr)) {
            return; // 중복인 경우 추가하지 않음
        }

        // 2. 책 ID를 리스트에 추가 (중복을 피하고, 최대 10개 유지)
        redisTemplate.opsForList().leftPush(listKey, bookIdxStr);
        redisTemplate.opsForList().trim(listKey, 0, 20); // 최대 20개 유지
        redisTemplate.expire(listKey, 7, TimeUnit.DAYS); // TTL 설정

        try {
            String cacheKey = BOOK_CACHE_KEY_PREFIX + ":" + bookCachingItem.idx();
            String bookJson = objectMapper.writeValueAsString(bookCachingItem);
            redisTemplate.opsForValue().setIfAbsent(cacheKey, bookJson, Duration.ofHours(1));
        } catch (JsonProcessingException e) {
            throw new BookException(BookExceptionType.BOOK_CACHING_ITEM_CAN_NOT_DESERIALIZING);
        }
    }

    public Set<String> getRecentlyViewedBookKeys() {
        Set<String> keys = new HashSet<>();
        String pattern = RECENTLY_VIEWED_BOOKS_PREFIX + ":*";
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(100).build(); // 매번 100개씩 가져오기

        try (Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(options)) {
            cursor.forEachRemaining(key -> keys.add(new String(key)));
        }
        return keys;
    }

    private BookCachingItem deserializeBookCachingItem(String json) {
        try {
            return objectMapper.readValue(json, BookCachingItem.class);
        } catch (JsonProcessingException e) {
            throw new BookException(BookExceptionType.BOOK_CACHING_ITEM_CAN_NOT_DESERIALIZING);
        }
    }

    private String serializeBook(BookCachingItem bookCachingItem) {
        try {
            return objectMapper.writeValueAsString(bookCachingItem);
        } catch (JsonProcessingException e) {
            throw new BookException(BookExceptionType.BOOK_CACHING_ITEM_CAN_NOT_SERIALIZING);
        }
    }

    public BookCachingItem convertToBookCachingItem(BookCachingItem cachedBook) {
        return new BookCachingItem(
                cachedBook.idx(),
                cachedBook.title(),
                cachedBook.author(),
                cachedBook.publisher(),
                cachedBook.summary(),
                cachedBook.content(),
                cachedBook.mbti(),
                cachedBook.isLike());
    }

    private static BookCachingItem updateLikeData(BookCachingItem bookCachingItem, Boolean likeDislike) {
        return new BookCachingItem(
                bookCachingItem.idx(),
                bookCachingItem.title(),
                bookCachingItem.author(),
                bookCachingItem.publisher(),
                bookCachingItem.summary(),
                bookCachingItem.content(),
                bookCachingItem.mbti(),
                likeDislike
        );
    }

    private static BookCachingItem fromBookToBookCachingItem(Book book, Boolean likeDislike) {
        return new BookCachingItem(
                book.getIdx(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getSummary(),
                book.getContent(),
                book.getMbti(),
                likeDislike
        );
    }

    //TODO 추후 like도 캐싱할 것인지에 대한 논의 필요
    /*public void cacheBookLikeStatus(Long childIdx, Long bookIdx, Boolean isLike) {
        String likeKey = BOOK_LIKE_CACHE_KEY_PREFIX + childIdx + ":" + bookIdx;
        redisTemplate.opsForValue().set(likeKey, String.valueOf(isLike), Duration.ofDays(7));
    }*/
}