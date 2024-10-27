package com.Familyship.checkkuleogi.domains.study.dto;

import com.Familyship.checkkuleogi.domains.study.domain.enums.ClassRate;
import lombok.Getter;
import lombok.Setter;

@Getter
public class StudyRequestDTO {
    @Setter
    Long studyId;
    String name;
    ClassRate classRate;
}
