package com.Familyship.checkkuleogi.domains.recommend.presentation;

import com.Familyship.checkkuleogi.domains.recommend.dto.RecommendResponseDto;

import java.util.List;

public interface RecommendUseCase {
    List<RecommendResponseDto> getRecommendByChildIdx(Long childIdx);
}
