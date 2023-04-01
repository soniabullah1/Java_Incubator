package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.BooksDTO;
import com.example.JavaAndSpringIncubator.entities.Books;
import com.example.JavaAndSpringIncubator.repositories.BooksRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BooksRepository booksRepository;

    Logger logger = LogManager.getLogger(BooksRepository.class.getName());
    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<BooksDTO> getBooks()
    {
        List<BooksDTO> books = BooksDTO.toDtos(booksRepository.findAll());
        return books;
    }
}
