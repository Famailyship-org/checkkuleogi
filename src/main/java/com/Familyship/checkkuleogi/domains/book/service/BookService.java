package com.Familyship.checkkuleogi.domains.book.service;


import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.dto.request.FeedbackOnBookRequest;

public interface BookService {
    BookResponse createBook(BookMBTIRequest request);

    BookResponse selectBookBy(Long idx);
    void feedbackOnBook(BookLikeRequest req);
}
