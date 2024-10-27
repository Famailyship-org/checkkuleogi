package com.Familyship.checkkuleogi.domains.study.repository;

import com.Familyship.checkkuleogi.domains.study.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> { //Data Access Layer
}
