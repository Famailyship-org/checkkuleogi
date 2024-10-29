package com.Familyship.checkkuleogi.domains.user.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CreateUserRequestDTO {
    private String id;
    private String password;
    private String confirmPassword;
    private String name;
    private String email;
    private String birthday;
    private String gender;
}
