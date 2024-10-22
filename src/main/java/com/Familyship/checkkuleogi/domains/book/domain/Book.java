package com.Familyship.checkkuleogi.domains.book.domain;

import com.Familyship.checkkuleogi.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 추가
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_idx")
    private Long idx;

    @Column(name = "book_title")
    private String title;

    @Column(name = "book_author")
    private String author;

    @Column(name = "book_publisher")
    private String publisher;

    @Column(name = "book_summary")
    private String summary;

    @Column(name = "book_content")
    private String content;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_mbti_id")
    private BookMBTI bookMBTI;
}
