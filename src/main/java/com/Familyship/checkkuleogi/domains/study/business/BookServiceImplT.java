package com.Familyship.checkkuleogi.domains.study.business;

import com.Familyship.checkkuleogi.domains.like.domain.BookLike;
import com.Familyship.checkkuleogi.domains.study.domain.BookT;
import com.Familyship.checkkuleogi.domains.study.dto.request.FeedbackOnBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.ReadBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.RegisterBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.request.UpdateBookReq;
import com.Familyship.checkkuleogi.domains.study.dto.response.FeedbackOnBookResp;
import com.Familyship.checkkuleogi.domains.study.dto.response.ReadBookResp;
import com.Familyship.checkkuleogi.domains.study.dto.response.RegisterBookResp;
import com.Familyship.checkkuleogi.domains.study.implementation.manager.BookManager;
import com.Familyship.checkkuleogi.domains.study.implementation.mapper.BookDtoMapper;
import com.Familyship.checkkuleogi.domains.study.implementation.mapper.BookLikeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImplT implements BookService { //Business Layer

    private final BookManager bookManager;
    private final BookDtoMapper bookDtoMapper;
    private final BookLikeDtoMapper bookLikeDtoMapper;

    @Override
    @Transactional
    public RegisterBookResp registerBook(RegisterBookReq req) {
        BookT bookT = bookManager.registerBook(req);
        return bookDtoMapper.toRegisterBookResp(bookT);
    }

    @Override
    @Transactional
    public void updateBook(Long bookIdx, UpdateBookReq req) {
        bookManager.updateBook(bookIdx, req);
    }

    @Override
    @Transactional
    public void deleteBook(Long bookIdx) {
        bookManager.deleteBook(bookIdx);
    }

    @Override
    @Transactional(readOnly = true)
    public ReadBookResp readBook(ReadBookReq req) {
        String content = bookManager.readBook(req);
        return bookDtoMapper.toReadBookResp(content);
    }

    @Override
    @Transactional
    public FeedbackOnBookResp feedbackOnBook(String token, FeedbackOnBookReq req) {
        BookLike bookLike = bookManager.feedbackOnBook(token, req);
        return bookLikeDtoMapper.toFeedbackOnBookResp(bookLike);
    }


}
