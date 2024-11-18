package com.Familyship.checkkuleogi.book.presentation;


import com.Familyship.checkkuleogi.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.book.dto.request.BookUpdateRequest;
import com.Familyship.checkkuleogi.book.dto.response.BookResponse;

import static com.Familyship.checkkuleogi.book.presentation.enums.BookControllerResp.*;
import static com.Familyship.checkkuleogi.global.presentation.CommonResponseEntity.success;
import com.Familyship.checkkuleogi.global.presentation.CommonResponseEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "http://localhost:3000") // React 앱의 URL을 허용
public class BookController {

    private final BookUseCase bookUseCase;

    @PostMapping("/admin")
    public CommonResponseEntity<BookResponse> createBook(@RequestBody BookMBTIRequest req) {
        return success(bookUseCase.createBook(req));
    }

    @GetMapping("")
    public CommonResponseEntity<List<BookResponse>> getAllBooks() {
        return success(bookUseCase.getAllBooks());
    }

    @PutMapping("/admin/{bookId}")
    public CommonResponseEntity<BookResponse> updateBook(@PathVariable Long bookId, @RequestBody BookUpdateRequest request) {
        return success(bookUseCase.updateBook(bookId, request));
    }

    @DeleteMapping("/admin/{bookId}")
    public CommonResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookUseCase.deleteBookById(bookId);
        return success(DELETE_BOOK_SUCCESS.getMessage());
    }

    @GetMapping("/{bookIdx}")
    public CommonResponseEntity<BookCachingItem> getBook(@RequestParam("kidIdx") Long childIdx, @PathVariable Long bookIdx) {
        return success(bookUseCase.getBook(childIdx, bookIdx));
    }

    @GetMapping("/{childIdx}/recent")
    public CommonResponseEntity<List<BookCachingItem>> getRecentlyViewedBooks(@PathVariable Long childIdx) {
        return success(bookUseCase.getRecentlyViewedBooks(childIdx));
    }

    @GetMapping("/{childIdx}/like")
    public CommonResponseEntity<List<BookCachingItem>> getLikedBooks(@PathVariable Long childIdx) {
        return success(bookUseCase.getLikedBooks(childIdx));
    }

    @GetMapping("/{childIdx}/recommend")
    public CommonResponseEntity<List<BookCachingItem>> getRecommendBooks(@PathVariable Long childIdx) {
        return success(bookUseCase.getRecommendBooks(childIdx));
    }

    @PostMapping("/like")
    public CommonResponseEntity<String> feedbackOnBook(@RequestBody BookLikeRequest req) {
        bookUseCase.feedbackOnBook(req);
        return success(FEEDBACK_ON_BOOK_SUCCESS.getMessage());
    }

    @DeleteMapping("/like")
    public CommonResponseEntity<String> cancelFeedbackOnBook(@RequestBody BookLikeRequest req) {
        bookUseCase.cancelFeedbackOnBook(req);
        return success(DELETE_FEEDBACK_ON_BOOK_SUCCESS.getMessage());
    }
}