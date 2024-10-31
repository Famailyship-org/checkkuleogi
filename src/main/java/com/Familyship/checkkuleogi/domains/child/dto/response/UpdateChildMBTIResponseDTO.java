package com.Familyship.checkkuleogi.domains.child.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateChildMBTIResponseDTO {
    private String childName;
    private String mbti;
}
