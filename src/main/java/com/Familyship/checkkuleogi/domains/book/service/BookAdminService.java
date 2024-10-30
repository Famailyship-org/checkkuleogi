package com.Familyship.checkkuleogi.domains.book.service;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookUpdateRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.implementation.BookAdminManager;
import com.Familyship.checkkuleogi.domains.book.implementation.BookManager;
import com.Familyship.checkkuleogi.domains.book.implementation.mapper.BookDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookAdminService extends BookServiceImpl {

    private BookDtoMapper bookDtoMapper;
    private BookManager bookManager;
    private BookAdminManager bookAdminManager;

    public BookAdminService(BookDtoMapper bookDtoMapper, BookManager bookManager, BookAdminManager bookAdminManager) {
        super(bookDtoMapper, bookManager, bookAdminManager);
    }

    @Transactional
    public BookResponse createBook(BookMBTIRequest req) {
        Book book = bookAdminManager.createBook(req);
        return bookDtoMapper.toBookResp(book);
    }

    @Transactional
    public void deleteBookById(Long bookId) {
        bookAdminManager.deleteBook(bookId);
    }

    @Transactional
    public BookResponse updateBook(Long bookId, BookUpdateRequest request) {
        Book updatedBook = bookAdminManager.updateBook(bookId, request);
        return bookDtoMapper.toBookResp(updatedBook);
    }
}
