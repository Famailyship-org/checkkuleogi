package com.Familyship.checkkuleogi.domains.child.exception;

import com.Familyship.checkkuleogi.global.exception.CustomRuntimeException;

public class ChildException extends CustomRuntimeException {
    public ChildException(ChildExceptionType message, Object... args) {super(String.valueOf(message), args);}
}
