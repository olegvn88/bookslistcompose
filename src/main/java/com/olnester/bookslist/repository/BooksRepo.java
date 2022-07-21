package com.olnester.bookslist.repository;

import com.olnester.bookslist.entity.BooksEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<BooksEntity, Long> {

    Optional<BooksEntity> findById(Long id);
    Optional<BooksEntity> findByBookName(String bookName);

}
