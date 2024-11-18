package com.Familyship.checkkuleogi.child.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateChildMBTIResponseDTO {
    private String childName;
    private String mbti;
}
