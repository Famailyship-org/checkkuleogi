package com.Familyship.checkkuleogi.domains.study.repository;

import com.Familyship.checkkuleogi.domains.study.domain.BookT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryT extends JpaRepository<BookT, Long> { //Data Access Layer
}
