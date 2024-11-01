package com.Familyship.checkkuleogi.domains.book.presentation;


import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookUpdateRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.service.BookService;
import static com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity.success;
import com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/book")
@CrossOrigin(origins = "http://localhost:3000") // React 앱의 URL을 허용
public class BookController {

    private final BookService bookService;

    @PostMapping("/admin")
    public CommonResponseEntity<BookResponse> createBook(@RequestBody BookMBTIRequest req) {
        return success(bookService.createBook(req));
    }

    @GetMapping("")
    public CommonResponseEntity<List<BookResponse>> getAllBooks() {
        return success(bookService.getAllBooks());
    }

    @PutMapping("/admin/{bookId}")
    public CommonResponseEntity<BookResponse> updateBook(@PathVariable Long bookId, @RequestBody BookUpdateRequest request) {
        return success(bookService.updateBook(bookId, request));
    }

    @DeleteMapping("/admin/{bookId}")
    public CommonResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return success("삭제 완료");
    }

    @GetMapping("/{bookIdx}")
    public CommonResponseEntity<BookCachingItem> getBook(@RequestParam("kidIdx") Long childIdx, @PathVariable Long bookIdx) {
        return success(bookService.getBook(childIdx, bookIdx));
    }

    @GetMapping("/{childIdx}/recent")
    public CommonResponseEntity<List<BookCachingItem>> getRecentlyViewedBooks(@PathVariable Long childIdx) {
        return success(bookService.getRecentlyViewedBooks(childIdx));
    }

    @PostMapping("/like")
    public CommonResponseEntity<String> feedbackOnBook(@RequestBody BookLikeRequest req) {
        bookService.feedbackOnBook(req);
        return success("피드백 반영 완료");
    }

    @DeleteMapping("/like")
    public CommonResponseEntity<String> cancelFeedbackOnBook(@RequestBody BookLikeRequest req) {
        bookService.cancelFeedbackOnBook(req);
        return success("피드백 삭제 완료");
    }

    @GetMapping("/{childIdx}/like")
    public CommonResponseEntity<List<BookCachingItem>> getLikedBooks(@PathVariable Long childIdx) {
        return success(bookService.getLikedBooks(childIdx));
    }
}