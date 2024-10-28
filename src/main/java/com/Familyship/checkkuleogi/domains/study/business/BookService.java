package com.Familyship.checkkuleogi.domains.study.business;

import com.Familyship.checkkuleogi.domains.study.dto.request.FeedbackOnBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.ReadBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.RegisterBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.UpdateBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.response.FeedbackOnBookResp;
import com.Familyship.checkkuleogi.domains.study.dto.response.ReadBookResp;
import com.Familyship.checkkuleogi.domains.study.dto.response.RegisterBookResp;


public interface BookService {  //Business Layer
    RegisterBookResp registerBook(RegisterBookReq req);
    void updateBook(Long bookIdx, UpdateBookReq req);
    void deleteBook(Long bookIdx);
    // 관리자 기능

    ReadBookResp readBook(ReadBookReq req);
    FeedbackOnBookResp feedbackOnBook(String token, FeedbackOnBookReq req);
    // 유저 기능
}
