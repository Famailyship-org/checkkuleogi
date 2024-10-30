package com.Familyship.checkkuleogi.domains.book.service;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookUpdateRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.implementation.BookAdminManager;
import com.Familyship.checkkuleogi.domains.book.implementation.BookManager;
import com.Familyship.checkkuleogi.domains.book.implementation.mapper.BookDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookUserService extends BookServiceImpl {

    private BookDtoMapper bookDtoMapper;
    private BookManager bookManager;
    private BookAdminManager bookAdminManager;

    public BookUserService(BookDtoMapper bookDtoMapper, BookManager bookManager, BookAdminManager bookAdminManager) {
        super(bookDtoMapper, bookManager, bookAdminManager);
    }

    @Transactional(readOnly = true)
    public void feedbackOnBook(BookLikeRequest req) {
        bookManager.feedbackOnBook(req);
    }

    @Transactional(readOnly = true)
    public List<BookCachingItem> getRecentlyViewedBooks(Long childIdx) {
        return bookManager.getRecentlyViewedBooks(childIdx);
    }

    @Transactional(readOnly = true)
    public List<BookCachingItem> getLikedBooks(Long childIdx) {
        return List.of();
    }
}
