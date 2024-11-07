package com.Familyship.checkkuleogi.domains.book.service;

import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookUpdateRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.implementation.BookManager;
import com.Familyship.checkkuleogi.domains.book.implementation.mapper.BookDtoMapper;
import com.Familyship.checkkuleogi.domains.book.implementation.BookAdminManager;
import com.Familyship.checkkuleogi.domains.book.presentation.BookUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.Familyship.checkkuleogi.domains.book.jpa.Book;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BookService implements BookUseCase {

    private final BookDtoMapper bookDtoMapper;
    private final BookManager bookManager;
    private final BookAdminManager bookAdminManager;

    @Override
    @Transactional
    public BookResponse createBook(BookMBTIRequest req) {
        Book book = bookAdminManager.createBook(req);
        return bookDtoMapper.toBookResp(book);
    }

    @Override
    @Transactional
    public void deleteBookById(Long bookId) {
        bookAdminManager.deleteBook(bookId);
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long bookId, BookUpdateRequest request) {
        Book updatedBook = bookAdminManager.updateBook(bookId, request);
        return bookDtoMapper.toBookResp(updatedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookAdminManager.getAllBooks();
        return books.stream()
                .map(bookDtoMapper::toBookResp)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookCachingItem getBook(Long childIdx, Long bookIdx) {
        return bookManager.getBook(childIdx, bookIdx);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCachingItem> getRecentlyViewedBooks(Long childIdx) {
        return bookManager.getRecentlyViewedBooks(childIdx);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCachingItem> getLikedBooks(Long childIdx) {
        return bookManager.getLikedBooks(childIdx);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookCachingItem> getRecommendBooks(Long childIdx) {
        return bookManager.getRecommendBooks(childIdx);
    }

    @Override
    public void feedbackOnBook(BookLikeRequest req) {
        bookManager.feedbackOnBook(req);
    }

    @Override
    public void cancelFeedbackOnBook(BookLikeRequest req) {
        bookManager.cancelFeedbackOnBook(req);
    }
}
