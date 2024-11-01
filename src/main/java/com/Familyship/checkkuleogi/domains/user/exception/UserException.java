package com.Familyship.checkkuleogi.domains.user.exception;

import com.Familyship.checkkuleogi.global.domain.exception.CustomRuntimeException;

public class UserException extends CustomRuntimeException {
    public UserException(UserExceptionType message, Object... args) {
        super(String.valueOf(message), args);
    }
}
