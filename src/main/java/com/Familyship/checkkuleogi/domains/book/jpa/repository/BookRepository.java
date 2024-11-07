package com.Familyship.checkkuleogi.domains.book.jpa.repository;

import com.Familyship.checkkuleogi.domains.book.jpa.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
