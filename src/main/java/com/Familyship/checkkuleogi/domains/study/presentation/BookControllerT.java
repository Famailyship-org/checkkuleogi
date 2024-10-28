package com.Familyship.checkkuleogi.domains.study.presentation;

import com.Familyship.checkkuleogi.domains.study.business.BookService;
import com.Familyship.checkkuleogi.domains.study.dto.request.FeedbackOnBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.ReadBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.RegisterBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.UpdateBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.response.FeedbackOnBookResp;
import com.Familyship.checkkuleogi.domains.study.dto.response.ReadBookResp;
import com.Familyship.checkkuleogi.domains.study.dto.response.RegisterBookResp;
import com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookControllerT { //Presentation Layer 계층

    private final BookService bookService;

    @PostMapping("/admin")
    public CommonResponseEntity<RegisterBookResp> registerBook(@RequestBody RegisterBookReq req) {
        RegisterBookResp resp = bookService.registerBook(req);
        return CommonResponseEntity.success(resp);
    }

    @PatchMapping("/admin/{bookIdx}")
    public CommonResponseEntity<String> updateBook(@PathVariable Long bookIdx, @RequestBody UpdateBookReq req) {
        bookService.updateBook(bookIdx, req);
        return CommonResponseEntity.success("수정 완료");
    }

    @DeleteMapping("/admin/{bookIdx}")
    public CommonResponseEntity<String> deleteBook(@PathVariable Long bookIdx) {
        bookService.deleteBook(bookIdx);
        return CommonResponseEntity.success("삭제 완료");
    }

    @GetMapping
    public CommonResponseEntity<ReadBookResp> readBook(@RequestBody ReadBookReq req) {
        ReadBookResp resp = bookService.readBook(req);
        return CommonResponseEntity.success(resp);
    }

    @PostMapping()
    public CommonResponseEntity<FeedbackOnBookResp> feedbackToBook(@RequestHeader("Authorization") String token, @RequestBody FeedbackOnBookReq req) {
        FeedbackOnBookResp resp = bookService.feedbackOnBook(token, req);
        return CommonResponseEntity.success(resp);
    }
}
