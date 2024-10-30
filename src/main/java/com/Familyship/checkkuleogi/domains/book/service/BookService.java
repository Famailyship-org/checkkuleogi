package com.Familyship.checkkuleogi.domains.book.service;


import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookUpdateRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.dto.request.FeedbackOnBookRequest;

import java.util.List;

public interface BookService {
    List<BookResponse> getAllBooks();
    BookResponse selectBookBy(Long ChildIdx, Long BookIdx);
}
