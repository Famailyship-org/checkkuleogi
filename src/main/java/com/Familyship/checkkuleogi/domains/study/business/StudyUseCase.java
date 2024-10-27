package com.Familyship.checkkuleogi.domains.study.business;

import com.Familyship.checkkuleogi.domains.study.dto.StudyRequestDTO;
import com.Familyship.checkkuleogi.domains.study.dto.StudyResponseDTO;

public interface StudyUseCase {
    StudyResponseDTO registerStudy(String token, StudyRequestDTO dto);
    StudyResponseDTO updateStudy(StudyRequestDTO dto);

    //반환 타입을 다 record로 바꿔주자. 진짜 값 전달만 하면 되니까
}
