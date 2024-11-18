package com.Familyship.checkkuleogi.user.exception;

import com.Familyship.checkkuleogi.global.exception.CustomRuntimeException;

public class UserException extends CustomRuntimeException {
    public UserException(UserExceptionType message, Object... args) {super(String.valueOf(message), args);}
}