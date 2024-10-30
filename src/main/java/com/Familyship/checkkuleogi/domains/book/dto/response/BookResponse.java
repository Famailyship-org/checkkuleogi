package com.Familyship.checkkuleogi.domains.book.dto.response;


public record BookResponse(Long idx, String title, String author, String publisher, String summary, String content, String mbti) {
}