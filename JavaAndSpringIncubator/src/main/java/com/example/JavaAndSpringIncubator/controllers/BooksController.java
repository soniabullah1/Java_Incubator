package com.example.JavaAndSpringIncubator.controllers;

import com.example.JavaAndSpringIncubator.dto.BooksDTO;
import com.example.JavaAndSpringIncubator.repositories.BooksRepository;
import com.example.JavaAndSpringIncubator.services.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    Logger logger = LogManager.getLogger(BooksRepository.class.getName());
    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<BooksDTO>> getBooks()
    {
        List<BooksDTO> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{bookID}")
    public ResponseEntity<BooksDTO> getBookByID(@PathVariable Integer bookID)
    {
        BooksDTO book = bookService.getBookByID(bookID);
        return ResponseEntity.ok(book);
    }
}
