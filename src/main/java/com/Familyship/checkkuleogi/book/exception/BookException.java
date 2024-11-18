package com.Familyship.checkkuleogi.book.exception;

import com.Familyship.checkkuleogi.global.exception.CustomRuntimeException;

public class BookException extends CustomRuntimeException {
    public BookException(BookExceptionType message, Object... args) {super(String.valueOf(message), args);}
}