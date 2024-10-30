package com.Familyship.checkkuleogi.domains.child.implementation;

import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.child.domain.repository.ChildRepository;
import com.Familyship.checkkuleogi.domains.child.exception.ChildException;
import com.Familyship.checkkuleogi.domains.child.exception.ChildExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

}
