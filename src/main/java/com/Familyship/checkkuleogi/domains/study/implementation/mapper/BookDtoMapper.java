package com.Familyship.checkkuleogi.domains.study.implementation.mapper;

import com.Familyship.checkkuleogi.domains.study.domain.BookT;
import com.Familyship.checkkuleogi.domains.study.dto.response.ReadBookResp;
import com.Familyship.checkkuleogi.domains.study.dto.response.RegisterBookResp;
import org.springframework.stereotype.Component;

@Component
public class BookDtoMapper { //Implement Layer 계층

    public RegisterBookResp toRegisterBookResp(BookT bookT) {
        return new RegisterBookResp("제목 : "+ bookT.getTitle(), "저자 : " + bookT.getAuthor());
    }

    public ReadBookResp toReadBookResp(String content) {
        return  new ReadBookResp("책 내용 : "+content);
    }
}
