package com.Familyship.checkkuleogi.domains.study.implementation.mapper;

import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.like.domain.BookLike;
import com.Familyship.checkkuleogi.domains.study.dto.response.FeedbackOnBookRespT;
import org.springframework.stereotype.Component;

@Component
public class BookLikeDtoMapperT {

    public FeedbackOnBookRespT toFeedbackOnBookResp(BookLike bookLike) {
        //BookT book = bookLike.getBook(); 기존 BookT 테이블과 충돌
        Child child = bookLike.getChild();
        String likeDisLike = bookLike.getLikedislike() ? "like" : "dislike";

        //return new FeedbackOnBookRespT(book.getIdx(), child.getIdx(), likeDisLike); 기존 BookT 테이블과 충돌
        return new FeedbackOnBookRespT(1L, child.getIdx(), likeDisLike);
    }
}
