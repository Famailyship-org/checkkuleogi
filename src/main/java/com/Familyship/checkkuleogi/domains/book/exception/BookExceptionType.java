package com.Familyship.checkkuleogi.domains.book.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum BookExceptionType {
    BOOK_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 책을 찾을 수 없습니다"),
    BOOK_NOT_FOUND_CACHE(HttpStatus.NOT_FOUND, "최근 한달 간 조회한 책이 없습니다"),
    BOOK_LIKE_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "좋아요 기록을 찾을 수 없습니다"),
    BOOK_CACHING_ITEM_CAN_NOT_DESERIALIZING(HttpStatus.FAILED_DEPENDENCY, "캐싱 데이터 역직렬화에 실패했습니다"),
    BOOK_CACHING_ITEM_CAN_NOT_SERIALIZING(HttpStatus.FAILED_DEPENDENCY, "Book 엔티티 직렬화에 실패했습니다");

    private final HttpStatus status;
    private final String message;
}
