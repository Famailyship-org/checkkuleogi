package com.Familyship.checkkuleogi.domains.book.implementation;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.domain.repository.BookRepository;
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

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class BookManager {
    private final BookRepository bookRepository;
    private final BookLikeRepository bookLikeRepository;

    private final JwtProvider jwtProvider;
    private final ChildManager childManager;

    public Book selectBookBy(Long idx) {
        return this.findBookBy(idx);
    }

    public void feedbackOnBook(BookLikeRequest req) {
        Book book = this.findBookBy(req.bookIdx());
        Child child = childManager.findChildBy(req.childIdx());

        BookLike bookLike = BookLike.builder()
                .child(child)
                .book(book)
                .likedislike(req.isLike())
                .build();
        bookLikeRepository.save(bookLike);
    }

    public Book findBookBy(Long idx) {
        return bookRepository.findById(idx)
                .orElseThrow(() -> new BookException(BookExceptionType.BOOK_NOT_FOUND_EXCEPTION));
    }
}