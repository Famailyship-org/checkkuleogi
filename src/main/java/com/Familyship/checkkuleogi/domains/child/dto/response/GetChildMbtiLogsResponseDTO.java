package com.Familyship.checkkuleogi.domains.child.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetChildMbtiLogsResponseDTO {
    private List<Integer> mbti_e;
    private List<Integer> mbti_j;
    private List<Integer> mbti_s;
    private List<Integer> mbti_t;
}
