package com.Familyship.checkkuleogi.child.implementation.mapper;



import com.Familyship.checkkuleogi.child.dto.request.LoginChildRequestDTO;
import com.Familyship.checkkuleogi.child.dto.response.CreateChildResponseDTO;
import com.Familyship.checkkuleogi.child.jpa.Child;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChildDtoMapper {
    public LoginChildRequestDTO toLoginChildReq(List<Long> childIds) {
        return new LoginChildRequestDTO(childIds);
    }

    public CreateChildResponseDTO toChildResp(Child child) {
        return new CreateChildResponseDTO(
                child.getIdx(),
                child.getName(),
                child.getGender(),
                child.getAge(),
                child.getBirthday());
    }
}
