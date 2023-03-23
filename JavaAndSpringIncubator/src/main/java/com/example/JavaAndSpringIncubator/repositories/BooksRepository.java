package com.example.JavaAndSpringIncubator.repositories;

import com.example.JavaAndSpringIncubator.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
}
