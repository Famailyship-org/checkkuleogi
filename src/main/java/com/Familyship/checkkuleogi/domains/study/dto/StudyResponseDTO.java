package com.Familyship.checkkuleogi.domains.study.dto;

import com.Familyship.checkkuleogi.domains.study.domain.enums.ClassRate;
import lombok.AllArgsConstructor;
import lombok.Getter;

// @Setter 사용 자제, DTO는 값 전달만을 위해 쓰이는 객체 데이터 전달 책임만 갖도록 구조를 단순화합니다.
@Getter
@AllArgsConstructor
public class StudyResponseDTO {
    Long id;
    String name;
    ClassRate classRate;
}
