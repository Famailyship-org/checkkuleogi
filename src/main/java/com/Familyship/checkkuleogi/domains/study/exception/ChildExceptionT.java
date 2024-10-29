package com.Familyship.checkkuleogi.domains.study.exception;

import com.Familyship.checkkuleogi.global.domain.exception.CustomRuntimeException;

public class ChildExceptionT extends CustomRuntimeException {
    public ChildExceptionT(ChildExceptionTypeT message, Object... args) {super(String.valueOf(message), args);}
}
