package com.Familyship.checkkuleogi.domains.study.presentation;

import com.Familyship.checkkuleogi.domains.study.dto.StudyRequestDTO;
import com.Familyship.checkkuleogi.domains.study.dto.StudyResponseDTO;
import com.Familyship.checkkuleogi.domains.study.business.StudyUseCase;
import com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/study")
public class StudyController {

    private final JwtProvider jwtProvider;
    private final StudyUseCase studyUsecase; //컨트롤러는 바로 아래 단계만 바라봐 여기 매니저 이딴 저급한애들 못 끼고 바로 아래 단계인 Usecase의 구현체인(Service)

    @GetMapping("")
    public CommonResponseEntity<StudyResponseDTO> creatStudy(@RequestHeader("Authorization") String token,
                                                   @RequestBody StudyRequestDTO dto) {
        return success(studyUsecase.registerStudy(token, dto));
    }
}
