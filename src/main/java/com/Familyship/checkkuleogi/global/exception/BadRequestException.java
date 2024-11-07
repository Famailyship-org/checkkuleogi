package com.Familyship.checkkuleogi.global.exception;

public class BadRequestException extends CustomRuntimeException {
    public BadRequestException(String message, Object... args) {
        super(message, args);
    }
}
