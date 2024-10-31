package com.Familyship.checkkuleogi.domains.child.implementation;


import com.Familyship.checkkuleogi.domains.child.domain.repository.ChildMBTILogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PhysicalDeleteScheduler {
    private final ChildMBTILogRepository childMBTILogRepository;

    @Scheduled(cron = "0 1 0 * * *")
    public void deleteOldRecords() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        childMBTILogRepository.deleteByDeletedIsTrueAndDeletedAtBefore(oneMonthAgo);
    }

//    @Scheduled(cron = "0 */3 * * * *") // 테스트용(3분마다 실행)
//    public void deleteOldRecords() {
//        LocalDateTime minutesAgo = LocalDateTime.now().minusMinutes(3);
//        childMBTILogRepository.deleteByDeletedIsTrueAndDeletedAtBefore(minutesAgo);
//    }
}
