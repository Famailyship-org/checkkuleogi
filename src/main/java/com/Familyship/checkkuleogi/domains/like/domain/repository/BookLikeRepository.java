package com.Familyship.checkkuleogi.domains.like.domain.repository;

import com.Familyship.checkkuleogi.domains.book.domain.Book;
import com.Familyship.checkkuleogi.domains.child.domain.Child;
import com.Familyship.checkkuleogi.domains.like.domain.BookLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookLikeRepository extends JpaRepository<BookLike, Long> {
    Optional<BookLike> findByChildIdxAndBookIdx(Long childIdx, Long bookIdx);

    Optional<BookLike> findByBookAndChild(Book book, Child child);

    @Query("SELECT bl.book.idx FROM BookLike bl WHERE bl.child.idx = :childIdx")
    List<Long> findBookIdsByChildIdx(@Param("childIdx") Long childIdx);
}
