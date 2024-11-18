package com.Familyship.checkkuleogi.book.jpa.repository;

import com.Familyship.checkkuleogi.book.jpa.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
