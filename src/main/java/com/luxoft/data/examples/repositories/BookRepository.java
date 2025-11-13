package com.luxoft.data.examples.repositories;

import com.luxoft.data.examples.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(String title);
    
    List<Book> findAllByOrderByTitleAsc();

    List<Book> findByReleaseDateBetween(LocalDate start, LocalDate end);

}
