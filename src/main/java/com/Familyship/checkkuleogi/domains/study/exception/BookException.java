package com.Familyship.checkkuleogi.domains.study.exception;

import com.Familyship.checkkuleogi.global.domain.exception.CustomRuntimeException;

public class BookException extends CustomRuntimeException {
    public BookException(BookExceptionType message, Object... args) {super(String.valueOf(message), args);}
}
