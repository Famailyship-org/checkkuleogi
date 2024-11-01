package com.Familyship.checkkuleogi.domains.child.domain;

import com.Familyship.checkkuleogi.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "child_mbti")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChildMBTI extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_mbti_idx")
    private Long idx;

    @Column(name = "mbti_e")
    private Integer mbtiE;

    @Column(name = "mbti_s")
    private Integer mbtiS;

    @Column(name = "mbti_t")
    private Integer mbtiT;

    @Column(name = "mbti_j")
    private Integer mbtiJ;

    @Column(name = "child_idx", unique = true)
    private Long childIdx;
}