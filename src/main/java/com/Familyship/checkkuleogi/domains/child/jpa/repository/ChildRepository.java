package com.Familyship.checkkuleogi.domains.child.jpa.repository;

import com.Familyship.checkkuleogi.domains.child.jpa.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findByName(String name);
    Optional<Child> findByIdx(Long id);

    @Query("SELECT c.idx FROM Child c WHERE c.parent.idx= :parentId")
    List<Long> findIdsByParentId(@Param("parentId") Long parentId);

    @Modifying
    @Transactional
    @Query("UPDATE Child c SET c.isActive = true WHERE c.idx IN :activeUserIds")
    void markUsersAsActive(Set<Long> activeUserIds);

    @Modifying
    @Transactional
    @Query("UPDATE Child c SET c.isActive = false WHERE c.idx NOT IN :activeUserIds")
    void markUsersAsInactiveNotIn(Set<Long> activeUserIds);
}

