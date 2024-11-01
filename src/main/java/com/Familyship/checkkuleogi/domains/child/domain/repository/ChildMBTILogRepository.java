package com.Familyship.checkkuleogi.domains.child.domain.repository;

import com.Familyship.checkkuleogi.domains.child.domain.ChildMBTILog;
import com.Familyship.checkkuleogi.domains.child.dto.response.GetChildMbtiLogsResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ChildMBTILogRepository extends JpaRepository<ChildMBTILog, Long> {

    @Query("SELECT m.mbtiE, m.mbtiJ, m.mbtiS, m.mbtiT " +
            "FROM ChildMBTILog m WHERE m.childIdx = :childIdx ORDER BY m.updatedAt desc ")
    List<Object[]> findTopNByChildIdxOrderByUpdatedAtDesc(@Param("childIdx") Long childIdx, Pageable pageable);
}
