package com.Familyship.checkkuleogi.book.implementation;

import com.Familyship.checkkuleogi.book.exception.BookException;
import com.Familyship.checkkuleogi.book.exception.BookExceptionType;
import com.Familyship.checkkuleogi.book.jpa.repository.BookLikeRepository;
import com.Familyship.checkkuleogi.book.jpa.repository.BookRepository;
import com.Familyship.checkkuleogi.book.jpa.Book;
import com.Familyship.checkkuleogi.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.child.jpa.Child;
import com.Familyship.checkkuleogi.child.implementation.ChildManager;
import com.Familyship.checkkuleogi.book.jpa.BookLike;
import com.Familyship.checkkuleogi.recommend.jpa.Recommend;
import com.Familyship.checkkuleogi.recommend.jpa.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookManager {
    private final BookRepository bookRepository;
    private final BookLikeRepository bookLikeRepository;
    private final RecommendRepository recommendRepository;

    private final ChildManager childManager;
    private final BookCacheManager bookCacheManager;

    public BookCachingItem getBook(Long childIdx, Long bookIdx) {
        // 캐시에서 먼저 조회 (캐시에 없을 경우 DB에서 조회하여 캐시에 저장)
        BookCachingItem cachedBook = bookCacheManager.findBookFromCacheOrDB(childIdx, bookIdx);

        // 최근 본 책 목록에 해당 책 추가
        bookCacheManager.cacheRecentlyViewedBook(cachedBook, childIdx);
        return bookCacheManager.convertToBookCachingItem(cachedBook);
    }

    public List<BookCachingItem> getRecentlyViewedBooks(Long childIdx) {
        List<BookCachingItem> recentlyViewedBooks = bookCacheManager.findBookListBy(childIdx);

        if (recentlyViewedBooks.isEmpty()) {
            throw new BookException(BookExceptionType.BOOK_NOT_FOUND_EXCEPTION);
        }
        return recentlyViewedBooks;
    }

    public List<BookCachingItem> getLikedBooks(Long childIdx) {
        List<Long> likedBookIds = bookLikeRepository.findBookIdsByChildIdx(childIdx);
        List<BookCachingItem> likedBooks = new ArrayList<>();

        for (Long bookId : likedBookIds) {
            // 책 캐시에서 메타데이터 가져오기, 없을 경우 DB 조회 후 캐시에 저장
            BookCachingItem cachedBook = bookCacheManager.findBookFromCacheOrDB(childIdx, bookId);
            likedBooks.add(cachedBook);
        }
        return likedBooks;
    }

    public List<BookCachingItem> getRecommendBooks(Long childIdx) {
        List<Recommend> recommendations = recommendRepository.findByChildIdx(childIdx);
        List<BookCachingItem> recommendedBooks = new ArrayList<>();

        for (Recommend recommend : recommendations) {
            // 책 카시에서 메타데이터 검색하기, 없을 경우 DB 조회 후 캐시에 저장
            BookCachingItem cachedBook = bookCacheManager.findBookFromCacheOrDB(childIdx, recommend.getBookIdx());
            recommendedBooks.add(cachedBook);
        }
        return recommendedBooks;
    }

    public void feedbackOnBook(BookLikeRequest req) {
        Book book = this.findBookBy(req.bookIdx());
        Child child = childManager.findChildBy(req.childIdx());

        bookLikeRepository.findByBookAndChild(book, child)
                .ifPresentOrElse(
                        existingLike -> existingLike.updateLikedislike(req.isLike()),
                        () -> {
                            BookLike bookLike = BookLike.builder()
                                    .child(child)
                                    .book(book)
                                    .likedislike(req.isLike())
                                    .build();
                            bookLikeRepository.save(bookLike);
                        });
    }

    public void cancelFeedbackOnBook(BookLikeRequest req) {
        BookLike bookLike = this.findBookLikeBy(req.childIdx(), req.bookIdx());
        bookLikeRepository.delete(bookLike);
    }

    public Book findBookBy(Long bookIdx) {
        return bookRepository.findById(bookIdx)
                .orElseThrow(() -> new BookException(BookExceptionType.BOOK_NOT_FOUND_EXCEPTION));
    }

    public BookLike findBookLikeBy(Long childIdx, Long bookIdx) {
        return bookLikeRepository.findByChildIdxAndBookIdx(childIdx, bookIdx)
                .orElseThrow(() -> new BookException(BookExceptionType.BOOK_LIKE_NOT_FOUND_EXCEPTION));
    }
}