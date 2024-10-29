package com.Familyship.checkkuleogi.domains.study.business;

import com.Familyship.checkkuleogi.domains.study.dto.request.FeedbackOnBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.request.ReadBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.request.RegisterBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.request.UpdateBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.response.FeedbackOnBookRespT;
import com.Familyship.checkkuleogi.domains.study.dto.response.ReadBookRespT;
import com.Familyship.checkkuleogi.domains.study.dto.response.RegisterBookRespT;


public interface BookServiceT {  //Business Layer
    RegisterBookRespT registerBook(RegisterBookReqT req);
    void updateBook(Long bookIdx, UpdateBookReqT req);
    void deleteBook(Long bookIdx);
    // 관리자 기능

    ReadBookRespT readBook(ReadBookReqT req);
    FeedbackOnBookRespT feedbackOnBook(String token, FeedbackOnBookReqT req);
    // 유저 기능
}
