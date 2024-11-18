package com.Familyship.checkkuleogi.book.jpa;

import com.Familyship.checkkuleogi.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_mbti")
public class BookMBTI extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_mbti_idx")
    private Long idx;

    @Column(name = "mbti_e")
    private Integer mbtiE;

    @Column(name = "mbti_s")
    private Integer mbtiS;

    @Column(name = "mbti_t")
    private Integer mbtiT;

    @Column(name = "mbti_j")
    private Integer mbtiJ;

    @Column(name = "book_idx")
    private Long bookIdx;
}
