package com.Familyship.checkkuleogi.domains.study.dto.request;

import com.Familyship.checkkuleogi.domains.study.domain.enums.BookStateT;

public record UpdateBookReqT(String title, String author, String publisher, String content, BookStateT state) {}
