package com.Familyship.checkkuleogi.domains.study.domain;
import com.Familyship.checkkuleogi.domains.study.domain.enums.BookStateT;
import com.Familyship.checkkuleogi.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "BookStudy")
public class BookT extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String content;

    private BookStateT state;

    public void update(String title, String author, String publisher, String content, BookStateT state) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.content = content;
        this.state = state;
    }

    @Builder
    private BookT(Long id, String title, String author, String publisher, String content, BookStateT state) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.content = content;
        this.state = state;
    }
}
