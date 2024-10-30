package com.Familyship.checkkuleogi.domains.book.implementation;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.domain.repository.BookRepository;
import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.exception.BookException;
import com.Familyship.checkkuleogi.domains.book.exception.BookExceptionType;
import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.child.implementation.ChildManager;
import com.Familyship.checkkuleogi.domains.like.domain.BookLike;
import com.Familyship.checkkuleogi.domains.like.domain.repository.BookLikeRepository;
import com.Familyship.checkkuleogi.global.domain.exception.NotFoundException;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookManager {
    private final BookRepository bookRepository;
    private final BookLikeRepository bookLikeRepository;

    private final ChildManager childManager;
    private final BookCacheManager bookCacheManager;

    public BookResponse selectBookBy(Long childIdx, Long bookIdx) {
        // 캐시에서 먼저 조회 (캐시에 없을 경우 DB에서 조회하여 캐시에 저장)
        BookCachingItem cachedBook = bookCacheManager.findBookFromCacheOrDB(childIdx, bookIdx);

        // 최근 본 책 목록에 해당 책 추가
        bookCacheManager.cacheRecentlyViewedBook(cachedBook, childIdx);
        return bookCacheManager.convertToBookResp(cachedBook);
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