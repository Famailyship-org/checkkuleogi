package com.Familyship.checkkuleogi.domains.book.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookMBTIRequest {
    private String title;
    private String author;
    private String publisher;
    private String summary;
    private String content;
}
