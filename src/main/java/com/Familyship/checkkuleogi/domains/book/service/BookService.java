package com.Familyship.checkkuleogi.domains.book.service;


import com.Familyship.checkkuleogi.domains.book.dto.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.BookMBTIResponse;

public interface BookService {
    BookMBTIResponse createBook(BookMBTIRequest request);
}
