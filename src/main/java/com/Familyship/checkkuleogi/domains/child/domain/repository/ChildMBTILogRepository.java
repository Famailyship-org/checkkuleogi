package com.Familyship.checkkuleogi.domains.child.domain.repository;

import com.Familyship.checkkuleogi.domains.child.domain.ChildMBTILog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ChildMBTILogRepository extends JpaRepository<ChildMBTILog, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM ChildMBTILog e WHERE e.isDeleted = true AND e.isDeletedAt < :dateTime")
    void deleteByDeletedIsTrueAndDeletedAtBefore(LocalDateTime dateTime);

    @Modifying
    @Transactional
    @Query("UPDATE ChildMBTILog c SET c.isDeleted = true, c.isDeletedAt = :deletedAt WHERE c.childIdx = :childIdx")
    void bulkDeleteByChildIdx(Long childIdx, LocalDateTime deletedAt);

}
