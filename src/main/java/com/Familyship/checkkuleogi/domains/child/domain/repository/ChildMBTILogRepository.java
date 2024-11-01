package com.Familyship.checkkuleogi.domains.child.domain.repository;

import com.Familyship.checkkuleogi.domains.child.domain.ChildMBTILog;
import com.Familyship.checkkuleogi.domains.child.dto.response.GetChildMbtiLogsResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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

    @Query("SELECT m.mbtiE, m.mbtiJ, m.mbtiS, m.mbtiT " +
            "FROM ChildMBTILog m WHERE m.childIdx = :childIdx ORDER BY m.updatedAt desc ")
    List<Object[]> findTopNByChildIdxOrderByUpdatedAtDesc(@Param("childIdx") Long childIdx, Pageable pageable);

}
