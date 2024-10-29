package com.Familyship.checkkuleogi.domains.book.exception;

import com.Familyship.checkkuleogi.domains.study.exception.BookExceptionTypeT;
import com.Familyship.checkkuleogi.global.domain.exception.CustomRuntimeException;
import lombok.AllArgsConstructor;

public class BookException extends CustomRuntimeException {
    public BookException(BookExceptionType message, Object... args) {super(String.valueOf(message), args);}
}