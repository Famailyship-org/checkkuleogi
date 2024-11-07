package com.Familyship.checkkuleogi.domains.book.presentation;


import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookUpdateRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;

import java.util.List;

public interface BookUseCase {
    // 관리자 기능
    BookResponse createBook(BookMBTIRequest request);
    void deleteBookById(Long bookId);
    List<BookResponse> getAllBooks();
    BookResponse updateBook(Long bookId, BookUpdateRequest request);

    // 유저 기능 (Select, CACHING)
    BookCachingItem getBook(Long ChildIdx, Long BookIdx);
    List<BookCachingItem> getRecentlyViewedBooks(Long childIdx);
    List<BookCachingItem> getLikedBooks(Long childIdx);
    List<BookCachingItem> getRecommendBooks(Long childIdx);

    // 유저 기능 (Update)
    void feedbackOnBook(BookLikeRequest req);
    void cancelFeedbackOnBook(BookLikeRequest req);
}
