package com.Familyship.checkkuleogi.book.dto.response;


public record BookResponse(Long idx, String title, String author, String publisher, String summary, String content, String mbti, Boolean isLike) {
}