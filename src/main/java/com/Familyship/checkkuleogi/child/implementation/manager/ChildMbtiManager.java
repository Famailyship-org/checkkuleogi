package com.Familyship.checkkuleogi.child.implementation.manager;

import com.Familyship.checkkuleogi.child.dto.response.GetChildMbtiLogsResponseDTO;
import com.Familyship.checkkuleogi.child.jpa.repository.ChildMBTILogRepository;
import com.Familyship.checkkuleogi.child.implementation.mapper.ChildMbtiDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ChildMbtiManager {

    private final ChildMBTILogRepository childMBTILogRepository;
    private final ChildMbtiDtoMapper childMbtiDtoMapper;

    public GetChildMbtiLogsResponseDTO getLatestMbtiValues(Long childIdx) {
        List<Object[]> results = childMBTILogRepository.findTopNByChildIdxOrderByUpdatedAtDesc(
                childIdx, PageRequest.of(0, 7)); // 최대 10개의 레코드를 가져옴

        List<Integer> mbtiE = new ArrayList<>();
        List<Integer> mbtiJ = new ArrayList<>();
        List<Integer> mbtiS = new ArrayList<>();
        List<Integer> mbtiT = new ArrayList<>();

        for (int i = results.size() - 1; i >= 0; i--) {
            Object[] result = results.get(i);
            mbtiE.add((Integer) result[0]);
            mbtiJ.add((Integer) result[1]);
            mbtiS.add((Integer) result[2]);
            mbtiT.add((Integer) result[3]);
        }

        return childMbtiDtoMapper.CreateResponseDto(mbtiE, mbtiJ, mbtiS, mbtiT);
    }


}
