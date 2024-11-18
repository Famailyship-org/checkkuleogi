package com.Familyship.checkkuleogi.user.exception;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserExceptionType {
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다.");

    private final HttpStatus status;
    private final String message;
}

