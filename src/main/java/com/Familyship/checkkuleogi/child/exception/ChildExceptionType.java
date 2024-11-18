package com.Familyship.checkkuleogi.child.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ChildExceptionType {
    CHILD_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 아이를 찾을 수 없습니다"),
    CHILD_NOT_EXIST_EXCEPTION(HttpStatus.NOT_FOUND, "아이가 존재 하지 않습니다.");
    private final HttpStatus status;
    private final String message;
}

