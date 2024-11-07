package com.Familyship.checkkuleogi.domains.recommend.service;

import com.Familyship.checkkuleogi.domains.book.jpa.repository.BookRepository;
import com.Familyship.checkkuleogi.domains.recommend.jpa.Recommend;
import com.Familyship.checkkuleogi.domains.recommend.jpa.repository.RecommendRepository;
import com.Familyship.checkkuleogi.domains.recommend.dto.RecommendResponseDto;
import com.Familyship.checkkuleogi.domains.recommend.presentation.RecommendUseCase;
import com.Familyship.checkkuleogi.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendService implements RecommendUseCase {
    private final RecommendRepository recommendRepository;
    private final BookRepository bookRepository;

    @Override
    public List<RecommendResponseDto> getRecommendByChildIdx(Long childIdx) {
        List<Recommend> recommendations = recommendRepository.findByChildIdx(childIdx);

        if(recommendations.isEmpty()) {
            throw new NotFoundException("추천 책 목록이 없습니다.");
        }

        return recommendations.stream()
                .map(recommend -> new RecommendResponseDto(recommend.getBookIdx()))
                .collect(Collectors.toList());
    }
}
