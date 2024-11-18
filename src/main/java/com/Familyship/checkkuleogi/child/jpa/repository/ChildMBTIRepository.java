package com.Familyship.checkkuleogi.child.jpa.repository;

import com.Familyship.checkkuleogi.child.jpa.ChildMBTI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChildMBTIRepository extends JpaRepository<ChildMBTI, Long> {
    Optional<ChildMBTI> findByIdx(Long idx);
}
