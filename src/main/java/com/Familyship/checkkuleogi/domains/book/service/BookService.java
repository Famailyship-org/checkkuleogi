package com.Familyship.checkkuleogi.domains.book.service;


import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookUpdateRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.dto.request.FeedbackOnBookRequest;

import java.util.List;

public interface BookService {
    BookResponse createBook(BookMBTIRequest request);
    void deleteBookById(Long bookId);
    List<BookResponse> getAllBooks();
    BookResponse updateBook(Long bookId, BookUpdateRequest request);


    BookResponse selectBookBy(Long ChildIdx, Long BookIdx);
    void feedbackOnBook(BookLikeRequest req);
    void cancelFeedbackOnBook(BookLikeRequest req);

    List<BookCachingItem> getRecentlyViewedBooks(Long childIdx);
    List<BookCachingItem> getLikedBooks(Long childIdx);
}
