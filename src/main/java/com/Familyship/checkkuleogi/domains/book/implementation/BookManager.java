package com.Familyship.checkkuleogi.domains.book.implementation;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.domain.repository.BookRepository;
import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.exception.BookException;
import com.Familyship.checkkuleogi.domains.book.exception.BookExceptionType;
import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.child.implementation.ChildManager;
import com.Familyship.checkkuleogi.domains.like.domain.BookLike;
import com.Familyship.checkkuleogi.domains.like.domain.repository.BookLikeRepository;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class BookManager {
    private final BookRepository bookRepository;
    private final BookLikeRepository bookLikeRepository;

    private final ChildManager childManager;
    private final BookCacheManager bookCacheManager;

    public Book selectBookBy(Long childIdx, Long bookIdx) {
        this.cacheRecentlyViewedBook(childIdx, bookIdx);
        return this.findBookBy(bookIdx);
    }

    public void feedbackOnBook(BookLikeRequest req) {
        Book book = this.findBookBy(req.bookIdx());
        Child child = childManager.findChildBy(req.childIdx());

        Optional<BookLike> existingLike = bookLikeRepository.findByBookAndChild(book, child);

        if (existingLike.isPresent()) { //업데이트
            BookLike bookLike = existingLike.get();
            bookLike.updateLikedislike(req.isLike());
            bookLikeRepository.save(bookLike);
        } else {  //생성
            BookLike bookLike = BookLike.builder()
                    .child(child)
                    .book(book)
                    .likedislike(req.isLike())
                    .build();
            bookLikeRepository.save(bookLike);
        }
    }

    public void cacheRecentlyViewedBook(Long childIdx, Long bookIdx) {
        Book book = this.findBookBy(bookIdx);
        Boolean isLike = null;

        Optional<BookLike> bookLike = bookLikeRepository.findByChild_IdxAndBook_Idx(childIdx, bookIdx);
        if (bookLike.isPresent()) {
            isLike = bookLike.get().getLikedislike();
        }

        BookCachingItem bookCachingItem = new BookCachingItem(
                book.getIdx(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getSummary(),
                book.getContent(),
                book.getMbti(),
                isLike
        );
        bookCacheManager.cacheRecentlyViewedBook(bookCachingItem, childIdx);
    }

    public Book findBookBy(Long idx) {
        return bookRepository.findById(idx)
                .orElseThrow(() -> new BookException(BookExceptionType.BOOK_NOT_FOUND_EXCEPTION));
    }

    public List<BookCachingItem> getRecentlyViewedBooks(Long childIdx) {
        return bookCacheManager.findBookListBy(childIdx)
                .orElseThrow(() -> new BookException(BookExceptionType.BOOK_NOT_FOUND_EXCEPTION));
    }
}