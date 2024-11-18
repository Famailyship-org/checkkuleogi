package com.Familyship.checkkuleogi.recommend.presentation;

import com.Familyship.checkkuleogi.recommend.dto.RecommendResponseDto;

import java.util.List;

public interface RecommendUseCase {
    List<RecommendResponseDto> getRecommendByChildIdx(Long childIdx);
}
