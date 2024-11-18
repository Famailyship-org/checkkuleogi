package com.Familyship.checkkuleogi.book.presentation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookControllerResp {
    DELETE_BOOK_SUCCESS("책 삭제 완료"),
    FEEDBACK_ON_BOOK_SUCCESS("책 피드백 반영 완료"),
    DELETE_FEEDBACK_ON_BOOK_SUCCESS("피드백 삭제 완료");

    private final String message;
}