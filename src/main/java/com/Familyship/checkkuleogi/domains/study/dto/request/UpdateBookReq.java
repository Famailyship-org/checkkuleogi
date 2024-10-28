package com.Familyship.checkkuleogi.domains.study.dto.request;

import com.Familyship.checkkuleogi.domains.study.domain.enums.BookState;

public record UpdateBookReq(String title, String author, String publisher, String content, BookState state) {}
