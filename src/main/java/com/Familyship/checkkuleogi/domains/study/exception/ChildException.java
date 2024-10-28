package com.Familyship.checkkuleogi.domains.study.exception;

import com.Familyship.checkkuleogi.global.domain.exception.CustomRuntimeException;

public class ChildException extends CustomRuntimeException {
    public ChildException(ChildExceptionType message, Object... args) {super(String.valueOf(message), args);}
}
