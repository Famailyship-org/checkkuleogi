package com.Familyship.checkkuleogi.domains.child.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateChildRequestDTO {
    private String name;
    private String gender;
    private Integer age;
    private LocalDate birth;
}
