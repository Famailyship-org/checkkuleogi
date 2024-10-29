package com.Familyship.checkkuleogi.domains.book.service;

import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.dto.request.FeedbackOnBookRequest;
import com.Familyship.checkkuleogi.domains.book.implementation.BookManager;
import com.Familyship.checkkuleogi.domains.book.implementation.mapper.BookDtoMapper;
import com.Familyship.checkkuleogi.domains.book.implementation.BookRegisterManager;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDtoMapper bookDtoMapper;
    private final BookManager bookManager;
    private final BookRegisterManager bookRegisterManager;

    @Override
    @Transactional
    public BookResponse createBook(BookMBTIRequest req) {
        Book book = bookRegisterManager.createBook(req);
        return bookDtoMapper.toBookResp(book);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse selectBookBy(Long idx) {
        Book book = bookManager.selectBookBy(idx);
        return bookDtoMapper.toBookResp(book);
    }

    @Override
    public void feedbackOnBook(BookLikeRequest req) {
        bookManager.feedbackOnBook(req);
    }
}
