package com.Familyship.checkkuleogi.domains.study.implementation.manager;

import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.child.domain.repository.ChildRepository;
import com.Familyship.checkkuleogi.domains.study.exception.ChildExceptionT;
import com.Familyship.checkkuleogi.domains.study.exception.ChildExceptionTypeT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChildManagerT {

    private final ChildRepository childRepository;

    public Child getChild(Long idx) {
        Optional<Child> child = childRepository.findById(idx);
        return child
                .orElseThrow(() -> new ChildExceptionT(ChildExceptionTypeT.CHILD_NOT_FOUNT_EXCEPTION));
    }

    public boolean checkExistsBy(Long idx) {
        return childRepository.existsById(idx);
    }
}
