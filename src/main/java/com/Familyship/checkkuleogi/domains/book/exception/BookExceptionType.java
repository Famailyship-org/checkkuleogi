package com.Familyship.checkkuleogi.domains.book.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum BookExceptionType {
    BOOK_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 책을 찾을 수 없습니다");

    private final HttpStatus status;
    private final String message;
}
