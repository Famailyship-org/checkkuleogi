package com.Familyship.checkkuleogi.domains.book.dto;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookMBTIResponse {
    private String title;
    private String author;
    private String publisher;
    private String summary;
    private String mbti;
}
