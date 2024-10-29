package com.Familyship.checkkuleogi.domains.book.presentation;


import com.Familyship.checkkuleogi.domains.book.dto.request.BookLikeRequest;
import com.Familyship.checkkuleogi.domains.book.dto.request.BookMBTIRequest;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.book.service.BookService;
import com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/admin")
    public CommonResponseEntity<BookResponse> createBook(@RequestBody BookMBTIRequest req) {
        return CommonResponseEntity.success(bookService.createBook(req));
    }

    @GetMapping("/{bookIdx}")
    public CommonResponseEntity<BookResponse> selectBook(@PathVariable Long bookIdx) {
        return CommonResponseEntity.success(bookService.selectBookBy(bookIdx));
    }

    @PostMapping("/like")
    public CommonResponseEntity<String> feedbackOnBook(@RequestBody BookLikeRequest req) {
        bookService.feedbackOnBook(req);
        return CommonResponseEntity.success("피드백 반영 완료");
    }
}