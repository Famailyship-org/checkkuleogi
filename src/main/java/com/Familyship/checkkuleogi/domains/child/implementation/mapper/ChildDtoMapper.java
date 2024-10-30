package com.Familyship.checkkuleogi.domains.child.implementation.mapper;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.dto.response.BookResponse;
import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.child.dto.LoginChildRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChildDtoMapper {
    public LoginChildRequestDTO toLoginChildReq(List<Long> childIds) {
        return new LoginChildRequestDTO(childIds);
    }
}
