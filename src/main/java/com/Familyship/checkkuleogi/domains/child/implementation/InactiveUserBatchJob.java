package com.Familyship.checkkuleogi.domains.child.implementation;

import com.Familyship.checkkuleogi.domains.book.implementation.BookCacheManager;
import com.Familyship.checkkuleogi.domains.child.domain.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InactiveUserBatchJob {

    private final ChildRepository childRepository;
    private final BookCacheManager bookCacheManager;

    @Transactional
    @Scheduled(cron = "0 0 0 1 * *")
    public void updateUserActivityStatus() {
        // 최근 본 책 목록을 가진 유저의 ID 추출
        Set<String> keys = bookCacheManager.getRecentlyViewedBookKeys();

        Set<Long> activeUserIds = keys.stream()
                .map(key -> Long.parseLong(key.split(":")[1]))
                .collect(Collectors.toSet());

        childRepository.markUsersAsActive(activeUserIds); // 활성 유저 업데이트
        childRepository.markUsersAsInactiveNotIn(activeUserIds); // 비활성 유저 업데이트
    }
}
