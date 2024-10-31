package com.Familyship.checkkuleogi.domains.child.implementation.mapper;



import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.child.dto.response.CreateChildResponseDTO;
import com.Familyship.checkkuleogi.domains.child.dto.request.LoginChildRequestDTO;
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
