package com.Familyship.checkkuleogi.domains.child.service;

import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.child.domain.ChildMBTI;
import com.Familyship.checkkuleogi.domains.child.domain.ChildMBTILog;
import com.Familyship.checkkuleogi.domains.child.domain.repository.ChildMBTILogRepository;
import com.Familyship.checkkuleogi.domains.child.domain.repository.ChildMBTIRepository;
import com.Familyship.checkkuleogi.domains.child.domain.repository.ChildRepository;
import com.Familyship.checkkuleogi.domains.child.dto.request.*;
import com.Familyship.checkkuleogi.domains.child.dto.response.*;
import com.Familyship.checkkuleogi.domains.child.implementation.ChildManager;

import com.Familyship.checkkuleogi.domains.child.implementation.manager.ChildMbtiManager;
import com.Familyship.checkkuleogi.domains.child.implementation.mapper.ChildDtoMapper;
import com.Familyship.checkkuleogi.domains.child.implementation.mapper.ChildMbtiDtoMapper;
import com.Familyship.checkkuleogi.domains.user.domain.SiteUser;
import com.Familyship.checkkuleogi.domains.user.domain.repository.UserRepository;
import com.Familyship.checkkuleogi.global.domain.exception.NotFoundException;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildServiceImpl implements ChildService {
    private final ChildMBTIRepository childMBTIRepository;
    private final ChildRepository childRepository;
    private final ChildMBTILogRepository childMBTILogRepository;
    private final JwtProvider jwtProvider;
    private final ChildManager childManager;
    private final ChildDtoMapper childDtoMapper;
    private final UserRepository userRepository;
    private final ChildMbtiManager childMbtiManager;
    private final ChildMbtiDtoMapper childMbtiDtoMapper;

    private static String calcMBTIResult(int length, int[] arr, int[] mbtiPercent, String mbtiResult) {
        // MBTI 설정 로직
        for (int i = 0; i < length; i++) {
            if (i == 0) { // 1번 문항 - E & I
                if (arr[i] > 0) { // yes - E 성향
                    mbtiPercent[i] = 100-Long.valueOf(Math.round((Math.abs(arr[i]) / 3.0) * 100)).intValue(); // E 성향 비율 계산
                    mbtiResult += "E";
                } else if (arr[i] < 0) { // no - I 성향
                    mbtiPercent[i] = Long.valueOf(Math.round((Math.abs(arr[i]) / 3.0) * 100)).intValue(); // I 성향 비율 계산
                    mbtiResult += "I";
                }


            } else if (i == 1) { // 2번 문항 - S & N
                if (arr[i] > 0) { // yes - S 성향
                    mbtiPercent[i] = 100-Long.valueOf(Math.round((Math.abs(arr[i]) / 3.0) * 100)).intValue();
                    mbtiResult += "S";
                } else if (arr[i] < 0) { // no - N 성향
                    mbtiPercent[i] = Long.valueOf(Math.round((Math.abs(arr[i]) / 3.0) * 100)).intValue();
                    mbtiResult += "N";
                }
            } else if (i == 2) { // 3번 문항 - T & F
                if (arr[i] > 0) { // yes - T 성향
                    mbtiPercent[i] = 100-Long.valueOf(Math.round((Math.abs(arr[i]) / 3.0) * 100)).intValue();
                    mbtiResult += "T";
                } else if (arr[i] < 0) { // no - F성향
                    mbtiPercent[i] = Long.valueOf(Math.round((Math.abs(arr[i]) / 3.0) * 100)).intValue();
                    mbtiResult += "F";
                }
            } else if (i == 3) { // 4번 문항 - J & P
                if (arr[i] > 0) { // yes - J 성향
                    mbtiPercent[i] = 100-Long.valueOf(Math.round((Math.abs(arr[i]) / 3.0) * 100)).intValue();
                    mbtiResult += "J";
                } else if (arr[i] < 0) { // no - P 성향
                    mbtiPercent[i] = Long.valueOf(Math.round((Math.abs(arr[i]) / 3.0) * 100)).intValue();
                    mbtiResult += "P";
                }
            }
        }
        return mbtiResult;
    }
    @Transactional
    @Override
    public CreateChildResponseMbtiDTO createMBTI(CreateChildRequestMbtiDTO createChildRequestMBTIDTO) {

        // UserId가 있는지 먼저 확인 -> 추후에 토큰 처리하기 때문에 빼버림
        // DTO를 하나씩 빼서 MBTI 성향 만들기
        Child child = isChildExisted(createChildRequestMBTIDTO.getChildName());

        // 문항마다 yes, no가 있고 해당 yes이면 E - I
        int[] temp = Arrays.stream(createChildRequestMBTIDTO.getSurveys()).toArray();
        int[] mbtiPercent = new int[createChildRequestMBTIDTO.getSurveys().length];
        String mbtiResult = "";
        mbtiResult = calcMBTIResult(mbtiPercent.length, temp, mbtiPercent, mbtiResult);

        // MBTI를 먼저 저장 해야함
        ChildMBTI childMBTI = saveMBTI(mbtiPercent, child.getIdx());

        System.out.println(mbtiResult);
        // MBTI 수치를 정해서 child 엔티티에 mbti를 update해야 함
        // Child 객체를 Builder를 통해 복사하고 mbti 값만 변경
        child.updateChildMbtiInfo(mbtiResult, childMBTI);
        System.out.println(child.getChildMBTI());
        return CreateChildResponseMbtiDTO.builder().
                name(child.getName()).
                age(child.getAge()).
                mbti(child.getMbti()).
                parentName(child.getParent().getName()).
                gender(child.getGender()).build();
    }

    @Override
    public CreateChildResponseDTO createChild(CreateChildRequestDTO createChildRequestDTO, String token) {
        Long parent_id = Long.valueOf(jwtProvider.getUserIdFromToken(token));
        SiteUser user = userRepository.findById(parent_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        Child child = childManager.createChild(createChildRequestDTO, user);
        return childDtoMapper.toChildResp(child);
    }

    private ChildMBTI saveMBTI(int[] mbtiPercent, Long childIdx) {
        ChildMBTI childMBTI = ChildMBTI.builder().
                mbtiE(mbtiPercent[0]).
                mbtiS(mbtiPercent[1]).
                mbtiT(mbtiPercent[2]).
                mbtiJ(mbtiPercent[3])
                .build();
        ChildMBTILog childMBTILog = ChildMBTILog.builder().
                mbtiE(mbtiPercent[0]).
                mbtiS(mbtiPercent[1]).
                mbtiT(mbtiPercent[2]).
                mbtiJ(mbtiPercent[3]).
                childIdx(childIdx).
                isSurveyResult(true).build();
        childMBTIRepository.save(childMBTI);
        childMBTILogRepository.save(childMBTILog);
        return childMBTI;
    }

    @Override
    public ReadChildResponseDTO readMBTI(ReadChildRequestDTO readChildRequestDTO) {
        Child child = isChildExisted(readChildRequestDTO.getChildName());

        return ReadChildResponseDTO.builder()
                .mbti(child.getMbti()).build();
    }

    @Override
    public LoginChildRequestDTO loginChild(String token) {
        Long parent_id = Long.valueOf(jwtProvider.getUserIdFromToken(token));
        List<Long> child_id = childManager.getChildIdby(parent_id);
        return childDtoMapper.toLoginChildReq(child_id);
    }

    @Transactional
    @Override
    public void deleteMBTI(DeleteChildMBTIRequestDTO deleteChildMBTIRequestDTO) {
        Child child = isChildExisted(deleteChildMBTIRequestDTO.getChildName());
        ChildMBTI childMBTI = childMBTIRepository.findByIdx(child.getChildMBTI().getIdx())
                .orElseThrow(() -> new NotFoundException("저장된 MBTI가 없습니다"));
        System.out.println(childMBTI);
        // child 엔티티 mbti 삭제
        child.updateChildMbtiInfo(null, null);
        // MBTI 로우 테이블 삭제
        childMBTIRepository.delete(childMBTI);
    }

    private Child isChildExisted(String childName) {
        return childRepository.findByName(childName)
                .orElseThrow(() -> new NotFoundException("아이가 등록되어 있지 않습니다"));
    }

    @Transactional
    @Override
    public UpdateChildMBTIResponseDTO updateMBTI(UpdateChildMBTIRequestDTO updateChildMBTIRequestDTO) {
        Child child = isChildExisted(updateChildMBTIRequestDTO.getChildName());
        if (child.getChildMBTI() == null) {
            throw new NotFoundException("저장된 MBTI가 없습니다.");
        }
        ChildMBTI childMBTI = childMBTIRepository.findByIdx(child.getChildMBTI().getIdx())
                .orElseThrow(() -> new NotFoundException("저장된 MBTI가 없습니다"));

        // 재검사 대상이 아닐 경우 새로운 검사로 API를 다시 요청하게 만든다.
        if (child.isMBTINull(child.getMbti())) {
            throw new NotFoundException("기존의 검사 결과가 없으므로 재검사 대상이 아닙니다.");
        }

        //기존의 MBTI를 먼저 삭제합니다
        childMBTIRepository.delete(childMBTI);

        int[] arr = Arrays.stream(updateChildMBTIRequestDTO.getSurveys()).toArray();
        int[] mbtiPercent = new int[updateChildMBTIRequestDTO.getSurveys().length];
        String mbtiResult = "";

        mbtiResult = calcMBTIResult(mbtiPercent.length, arr, mbtiPercent, mbtiResult);
        ChildMBTI updateChildMBTI = saveMBTI(mbtiPercent, child.getIdx());
        child.updateChildMbtiInfo(mbtiResult, updateChildMBTI);
        return UpdateChildMBTIResponseDTO.builder().childName(child.getName()).mbti(mbtiResult).build();
    }

    @Override
    @Transactional(readOnly = true)
    public ChildResponse getChildById(Long childIdx) {
        return childManager.getChildById(childIdx);
    }

    @Override
    @Transactional(readOnly = true)
    public GetChildMbtiLogsResponseDTO getMbtiLogs(Long childIdx) {
        return childMbtiManager.getLatestMbtiValues(childIdx);
    }
}
