package com.Familyship.checkkuleogi.domains.child.implementation.mapper;

import com.Familyship.checkkuleogi.domains.child.dto.response.GetChildMbtiLogsResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChildMbtiDtoMapper {

    public GetChildMbtiLogsResponseDTO CreateResponseDto(List<Integer> mbtiE, List<Integer> mbtiJ,
                                                         List<Integer> mbtiS, List<Integer> mbtiT){
        return GetChildMbtiLogsResponseDTO.builder()
                .mbti_e(mbtiE)
                .mbti_j(mbtiJ)
                .mbti_s(mbtiS)
                .mbti_t(mbtiT)
                .build();
    }


}
