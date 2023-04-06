package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.BooksDTO;
import com.example.JavaAndSpringIncubator.enums.BookStatus;

import java.util.List;

public interface IBookService {

    List<BooksDTO> getBooks();

    BooksDTO getBookByID(Integer bookID);

    BookStatus createBook(BooksDTO newBook);

    BookStatus deleteBook(Integer bookID);

    BooksDTO updateBook(BooksDTO booksDTO, Integer bookID);
}
