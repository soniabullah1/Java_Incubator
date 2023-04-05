package com.example.JavaAndSpringIncubator.controllers;

import com.example.JavaAndSpringIncubator.dto.BooksDTO;
import com.example.JavaAndSpringIncubator.dto.UserDTO;
import com.example.JavaAndSpringIncubator.entities.Books;
import com.example.JavaAndSpringIncubator.enums.BookStatus;
import com.example.JavaAndSpringIncubator.enums.UserStatus;
import com.example.JavaAndSpringIncubator.repositories.BooksRepository;
import com.example.JavaAndSpringIncubator.services.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/create")
    public ResponseEntity<BookStatus> createBook (@RequestBody BooksDTO book) {
        BookStatus bookStatus = bookService.createBook(book);
        return new ResponseEntity<>(bookStatus, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/{bookID}")
    public ResponseEntity<BookStatus> deleteBook (@PathVariable Integer bookID) {
        BookStatus bookStatus = bookService.deleteBook(bookID);
        return new ResponseEntity<>(bookStatus, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/update/{bookID}")
    public ResponseEntity<BooksDTO> updateBook(@RequestBody BooksDTO bookDTO,@PathVariable Integer bookID)
    {
        BooksDTO book = bookService.updateBook(bookDTO, bookID);
        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
    }

}
