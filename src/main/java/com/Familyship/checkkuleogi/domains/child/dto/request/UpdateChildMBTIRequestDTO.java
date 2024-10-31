package com.Familyship.checkkuleogi.domains.child.dto.request;

import lombok.Getter;

@Getter
public class UpdateChildMBTIRequestDTO {
    private String childName;
    private int[] surveys;
}
