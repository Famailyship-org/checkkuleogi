package com.Familyship.checkkuleogi.domains.study.implementation.mapper;

import com.Familyship.checkkuleogi.domains.study.domain.BookT;
import com.Familyship.checkkuleogi.domains.study.dto.response.ReadBookRespT;
import com.Familyship.checkkuleogi.domains.study.dto.response.RegisterBookRespT;
import org.springframework.stereotype.Component;

@Component
public class BookDtoMapperT { //Implement Layer 계층

    public RegisterBookRespT toRegisterBookResp(BookT bookT) {
        return new RegisterBookRespT("제목 : "+ bookT.getTitle(), "저자 : " + bookT.getAuthor());
    }

    public ReadBookRespT toReadBookResp(String content) {
        return  new ReadBookRespT("책 내용 : "+content);
    }
}
