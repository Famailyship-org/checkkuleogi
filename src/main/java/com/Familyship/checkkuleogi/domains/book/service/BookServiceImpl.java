package com.Familyship.checkkuleogi.domains.book.service;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
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
@AllArgsConstructor
public abstract class BookServiceImpl implements BookService {

    private final BookDtoMapper bookDtoMapper;
    private final BookManager bookManager;
    private final BookAdminManager bookAdminManager;

    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookAdminManager.getAllBooks();
        return books.stream()
                .map(bookDtoMapper::toBookResp)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookResponse selectBookBy(Long childIdx, Long bookIdx) {
        Book book = bookManager.selectBookBy(childIdx, bookIdx);
        return bookDtoMapper.toBookResp(book);
    }
}
