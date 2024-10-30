package com.Familyship.checkkuleogi.domains.child.domain.repository;

import com.Familyship.checkkuleogi.domains.child.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findByName(String name);


    @Query("SELECT c.idx FROM Child c WHERE c.parent.idx= :parentId")
    List<Long> findIdsByParentId(@Param("parentId") Long parentId);
}
