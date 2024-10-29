package com.Familyship.checkkuleogi.domains.study.presentation;

import com.Familyship.checkkuleogi.domains.study.business.BookServiceT;
import com.Familyship.checkkuleogi.domains.study.dto.request.FeedbackOnBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.request.ReadBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.request.RegisterBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.request.UpdateBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.response.FeedbackOnBookRespT;
import com.Familyship.checkkuleogi.domains.study.dto.response.ReadBookRespT;
import com.Familyship.checkkuleogi.domains.study.dto.response.RegisterBookRespT;
import com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookControllerT { //Presentation Layer 계층

    private final BookServiceT bookServiceT;

    @PostMapping("/admin")
    public CommonResponseEntity<RegisterBookRespT> registerBook(@RequestBody RegisterBookReqT req) {
        RegisterBookRespT resp = bookServiceT.registerBook(req);
        return CommonResponseEntity.success(resp);
    }

    @PatchMapping("/admin/{bookIdx}")
    public CommonResponseEntity<String> updateBook(@PathVariable Long bookIdx, @RequestBody UpdateBookReqT req) {
        bookServiceT.updateBook(bookIdx, req);
        return CommonResponseEntity.success("수정 완료");
    }

    @DeleteMapping("/admin/{bookIdx}")
    public CommonResponseEntity<String> deleteBook(@PathVariable Long bookIdx) {
        bookServiceT.deleteBook(bookIdx);
        return CommonResponseEntity.success("삭제 완료");
    }

    @GetMapping
    public CommonResponseEntity<ReadBookRespT> readBook(@RequestBody ReadBookReqT req) {
        ReadBookRespT resp = bookServiceT.readBook(req);
        return CommonResponseEntity.success(resp);
    }

    @PostMapping()
    public CommonResponseEntity<FeedbackOnBookRespT> feedbackToBook(@RequestHeader("Authorization") String token, @RequestBody FeedbackOnBookReqT req) {
        FeedbackOnBookRespT resp = bookServiceT.feedbackOnBook(token, req);
        return CommonResponseEntity.success(resp);
    }
}
