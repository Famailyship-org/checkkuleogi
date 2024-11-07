package com.Familyship.checkkuleogi.domains.child.implementation;

import com.Familyship.checkkuleogi.domains.child.jpa.Child;
import com.Familyship.checkkuleogi.domains.child.jpa.repository.ChildRepository;
import com.Familyship.checkkuleogi.domains.child.dto.request.CreateChildRequestDTO;
import com.Familyship.checkkuleogi.domains.child.dto.response.ChildResponse;
import com.Familyship.checkkuleogi.domains.child.exception.ChildException;
import com.Familyship.checkkuleogi.domains.child.exception.ChildExceptionType;
import com.Familyship.checkkuleogi.domains.user.jpa.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChildManager {
    private final ChildRepository childRepository;

    public Child findChildBy(Long idx) {
        return childRepository.findById(idx)
                .orElseThrow(() -> new ChildException(ChildExceptionType.CHILD_NOT_FOUND_EXCEPTION));
    }
    public List<Long> getChildIdby(Long parentId){
        List<Long> childIds = childRepository.findIdsByParentId(parentId);
        if (childIds.isEmpty()) {
            throw new ChildException(ChildExceptionType.CHILD_NOT_EXIST_EXCEPTION);
        }
        return childIds;
    }

    @Transactional // 데이터베이스 작업에 트랜잭션을 적용
    public Child createChild(CreateChildRequestDTO createChildRequestDTO, SiteUser user) {
        // 매퍼를 사용하여 Child 엔티티 생성
         Child child = Child.builder()
                .name(createChildRequestDTO.getName())
                 .age(createChildRequestDTO.getAge())
                 .gender(createChildRequestDTO.getGender())
                 .birthday(createChildRequestDTO.getBirth())
                 .isActive(true)
                 .parent(user)
                .build();

        // 데이터베이스에 저장하고 Child 객체 반환
        return childRepository.save(child);
    }

    // childIdx로 Child 정보를 조회
    public ChildResponse getChildById(Long childIdx) {
        Child child = childRepository.findById(childIdx)
                .orElseThrow(() -> new ChildException(ChildExceptionType.CHILD_NOT_FOUND_EXCEPTION));

        return new ChildResponse(
                child.getIdx(),
                child.getName(),
                child.getAge(),
                child.getGender(),
                child.getMbti()
        );
    }

}
