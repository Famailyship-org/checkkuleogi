package com.Familyship.checkkuleogi.domains.study.exception;

import com.Familyship.checkkuleogi.global.domain.exception.CustomRuntimeException;

public class BookExceptionT extends CustomRuntimeException {
    public BookExceptionT(BookExceptionTypeT message, Object... args) {super(String.valueOf(message), args);}
}
