package com.Familyship.checkkuleogi.domains.child.domain.repository;

import com.Familyship.checkkuleogi.domains.child.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Child c SET c.isActive = true WHERE c.idx IN :activeUserIds")
    void markUsersAsActive(Set<Long> activeUserIds);

    @Modifying
    @Transactional
    @Query("UPDATE Child c SET c.isActive = false WHERE c.idx NOT IN :activeUserIds")
    void markUsersAsInactiveNotIn(Set<Long> activeUserIds);
}
