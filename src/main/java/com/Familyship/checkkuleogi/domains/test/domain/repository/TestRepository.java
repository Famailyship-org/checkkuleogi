package com.Familyship.checkkuleogi.domains.test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
    Test findByIdx(Long idx);
}
