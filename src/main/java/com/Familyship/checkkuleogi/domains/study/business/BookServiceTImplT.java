package com.Familyship.checkkuleogi.domains.study.business;

import com.Familyship.checkkuleogi.domains.like.domain.BookLike;
import com.Familyship.checkkuleogi.domains.study.domain.BookT;
import com.Familyship.checkkuleogi.domains.study.dto.request.FeedbackOnBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.request.ReadBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.request.RegisterBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.request.UpdateBookReqT;
import com.Familyship.checkkuleogi.domains.study.dto.response.FeedbackOnBookRespT;
import com.Familyship.checkkuleogi.domains.study.dto.response.ReadBookRespT;
import com.Familyship.checkkuleogi.domains.study.dto.response.RegisterBookRespT;
import com.Familyship.checkkuleogi.domains.study.implementation.manager.BookManagerT;
import com.Familyship.checkkuleogi.domains.study.implementation.mapper.BookDtoMapperT;
import com.Familyship.checkkuleogi.domains.study.implementation.mapper.BookLikeDtoMapperT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceTImplT implements BookServiceT { //Business Layer

    private final BookManagerT bookManagerT;
    private final BookDtoMapperT bookDtoMapper;
    private final BookLikeDtoMapperT bookLikeDtoMapperT;

    @Override
    @Transactional
    public RegisterBookRespT registerBook(RegisterBookReqT req) {
        BookT bookT = bookManagerT.registerBook(req);
        return bookDtoMapper.toRegisterBookResp(bookT);
    }

    @Override
    @Transactional
    public void updateBook(Long bookIdx, UpdateBookReqT req) {
        bookManagerT.updateBook(bookIdx, req);
    }

    @Override
    @Transactional
    public void deleteBook(Long bookIdx) {
        bookManagerT.deleteBook(bookIdx);
    }

    @Override
    @Transactional(readOnly = true)
    public ReadBookRespT readBook(ReadBookReqT req) {
        String content = bookManagerT.readBook(req);
        return bookDtoMapper.toReadBookResp(content);
    }

    @Override
    @Transactional
    public FeedbackOnBookRespT feedbackOnBook(String token, FeedbackOnBookReqT req) {
        BookLike bookLike = bookManagerT.feedbackOnBook(token, req);
        return bookLikeDtoMapperT.toFeedbackOnBookResp(bookLike);
    }


}
