package com.Familyship.checkkuleogi.domains.book.presentation;


import com.Familyship.checkkuleogi.domains.book.dto.BookCachingItem;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
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
public class BookController {

    private final BookService bookService;

    @PostMapping("/admin")
    public CommonResponseEntity<BookResponse> createBook(@RequestBody BookMBTIRequest req) {
        return success(bookService.createBook(req));
    }

    @GetMapping("/{bookIdx}")
    public CommonResponseEntity<BookResponse> selectBook(@RequestParam("kidIdx") Long childIdx, @PathVariable Long bookIdx) {
        return success(bookService.selectBookBy(childIdx, bookIdx));
    }

    @PostMapping("/like")
    public CommonResponseEntity<String> feedbackOnBook(@RequestBody BookLikeRequest req) {
        bookService.feedbackOnBook(req);
        return success("피드백 반영 완료");
    }

    @GetMapping("/{childIdx}/recent")
    public CommonResponseEntity<List<BookCachingItem>> getRecentlyViewedBooks(@PathVariable Long childIdx) {
        return success(bookService.getRecentlyViewedBooks(childIdx));
    }

    @GetMapping("/{childIdx}/like")
    public CommonResponseEntity<List<BookCachingItem>> getLikedBooks(@PathVariable Long childIdx) {
        return success(bookService.getLikedBooks(childIdx));
    }
}