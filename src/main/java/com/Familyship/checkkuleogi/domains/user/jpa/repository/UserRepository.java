package com.Familyship.checkkuleogi.domains.user.jpa.repository;

import com.Familyship.checkkuleogi.domains.user.jpa.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByIdx(Long idx);
    Optional<SiteUser> findById(String id);
    String findNameByIdx(Long id);
}
