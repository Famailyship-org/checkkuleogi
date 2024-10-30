package com.Familyship.checkkuleogi.domains.child.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateChildResponseMbtiDTO {
    private String name;
    private int age;
    private String gender;
    private String mbti;
    private String parentName;
}
