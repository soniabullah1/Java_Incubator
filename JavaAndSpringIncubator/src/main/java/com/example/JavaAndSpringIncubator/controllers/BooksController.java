package com.example.JavaAndSpringIncubator.controllers;

import com.example.JavaAndSpringIncubator.dto.BooksDTO;
import com.example.JavaAndSpringIncubator.repositories.BooksRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private final BooksRepository booksRepository;

    Logger logger = LogManager.getLogger(BooksRepository.class.getName());

    public BooksController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<BooksDTO>> getBooks()
    {
        List<BooksDTO> books = BooksDTO.toDtos(booksRepository.findAll());
//        List<BooksDTO> limited = books.subList(80,100);
        return ResponseEntity.ok(books);
    }
}
