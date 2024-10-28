package com.Familyship.checkkuleogi.domains.study.presentation;

import com.Familyship.checkkuleogi.domains.study.business.BookService;
import com.Familyship.checkkuleogi.domains.study.dto.request.FeedbackOnBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.ReadBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.RegisterBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.UpdateBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.response.FeedbackOnBookResp;
import com.Familyship.checkkuleogi.domains.study.dto.response.ReadBookResp;
import com.Familyship.checkkuleogi.domains.study.dto.response.RegisterBookResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookControllerT { //Presentation Layer 계층

    private final BookService bookService;

    @PostMapping("/admin")
    public ResponseEntity<RegisterBookResp> registerBook(@RequestBody RegisterBookReq req) {
        RegisterBookResp resp = bookService.registerBook(req);
        return ResponseEntity.ok(resp);
    }

    @PatchMapping("/admin/{bookIdx}")
    public ResponseEntity<String> updateBook(@PathVariable Long bookIdx, @RequestBody UpdateBookReq req) {
        bookService.updateBook(bookIdx, req);
        return ResponseEntity.ok("수정 완료");
    }

    @DeleteMapping("/admin/{bookIdx}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookIdx) {
        bookService.deleteBook(bookIdx);
        return ResponseEntity.ok("삭제 완료");
    }

    @GetMapping
    public ResponseEntity<ReadBookResp> readBook(@RequestBody ReadBookReq req) {
        ReadBookResp resp = bookService.readBook(req);
        return ResponseEntity.ok(resp);
    }

    @PostMapping()
    public ResponseEntity<FeedbackOnBookResp> feedbackToBook(@RequestHeader("Authorization") String token, @RequestBody FeedbackOnBookReq req) {
        FeedbackOnBookResp resp = bookService.feedbackOnBook(token, req);
        return ResponseEntity.ok(resp);
    }
}
