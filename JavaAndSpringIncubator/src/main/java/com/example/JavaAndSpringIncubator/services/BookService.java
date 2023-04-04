package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.BooksDTO;
import com.example.JavaAndSpringIncubator.entities.Books;
import com.example.JavaAndSpringIncubator.repositories.BooksRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

    public BooksDTO getBookByID(Integer bookID)
    {
        Optional<Books> books = booksRepository.findById(bookID);

            return BooksDTO.fromEntity(books.get());
    }
}
