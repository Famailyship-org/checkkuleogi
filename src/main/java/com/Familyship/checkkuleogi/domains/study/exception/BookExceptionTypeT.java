package com.Familyship.checkkuleogi.domains.study.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum BookExceptionTypeT {
    BOOK_NOT_FOUNT_EXCEPTION(HttpStatus.NOT_FOUND, "해당 책을 찾을 수 없습니다");

    private final HttpStatus status;
    private final String message;
}
