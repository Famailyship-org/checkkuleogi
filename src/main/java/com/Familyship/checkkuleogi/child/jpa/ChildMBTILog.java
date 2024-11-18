package com.Familyship.checkkuleogi.child.jpa;

import com.Familyship.checkkuleogi.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Table(name = "child_mbti_log")
@AllArgsConstructor
@NoArgsConstructor
public class ChildMBTILog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_mbti_log_idx")
    private Long idx;

    @Column
    private Long childIdx;

    @Column(name = "mbti_e")
    private Integer mbtiE;

    @Column(name = "mbti_s")
    private Integer mbtiS;

    @Column(name = "mbti_t")
    private Integer mbtiT;

    @Column(name = "mbti_j")
    private Integer mbtiJ;

    @Column(name = "is_survey_result")
    private Boolean isSurveyResult;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_deleted_at")
    private LocalDateTime isDeletedAt;


}