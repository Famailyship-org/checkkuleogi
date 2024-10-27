package com.Familyship.checkkuleogi.domains.study.domain;
import com.Familyship.checkkuleogi.domains.study.domain.enums.BookState;
import com.Familyship.checkkuleogi.global.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @Entity
public class Book extends BaseEntity {
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String content;

    private BookState state;

    public Book update(String title, String author, String publisher, String content, BookState state) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.content = content;
        this.state = state;
        return this;
    }

    @Builder
    private Book(Long id, String title, String author, String publisher, String content, BookState state) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.content = content;
        this.state = state;
    }
}
