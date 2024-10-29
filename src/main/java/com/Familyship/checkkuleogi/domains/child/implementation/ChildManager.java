package com.Familyship.checkkuleogi.domains.child.implementation;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.book.exception.BookException;
import com.Familyship.checkkuleogi.domains.book.exception.BookExceptionType;
import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.child.domain.repository.ChildRepository;
import com.Familyship.checkkuleogi.domains.child.exception.ChildException;
import com.Familyship.checkkuleogi.domains.child.exception.ChildExceptionType;
import com.Familyship.checkkuleogi.domains.study.exception.ChildExceptionT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChildManager {
    private final ChildRepository childRepository;

    public Child findChildBy(Long idx) {
        return childRepository.findById(idx)
                .orElseThrow(() -> new ChildException(ChildExceptionType.CHILD_NOT_FOUND_EXCEPTION));
    }

}
