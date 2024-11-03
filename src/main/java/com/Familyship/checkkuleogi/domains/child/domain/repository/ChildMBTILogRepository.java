package com.Familyship.checkkuleogi.domains.child.domain.repository;

import com.Familyship.checkkuleogi.domains.child.domain.ChildMBTILog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChildMBTILogRepository extends JpaRepository<ChildMBTILog, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE ChildMBTILog c SET c.isDeleted = true, c.isDeletedAt = :deletedAt WHERE c.childIdx = :childIdx")
    void bulkDeleteByChildIdx(Long childIdx, LocalDateTime deletedAt);


    @Query("SELECT m.mbtiE, m.mbtiJ, m.mbtiS, m.mbtiT " +
            "FROM ChildMBTILog m WHERE m.childIdx = :childIdx and m.isDeleted = false ORDER BY m.updatedAt desc ")
    List<Object[]> findTopNByChildIdxOrderByUpdatedAtDesc(@Param("childIdx") Long childIdx, Pageable pageable);
}



