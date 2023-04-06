package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.BooksDTO;
import com.example.JavaAndSpringIncubator.entities.Books;
import com.example.JavaAndSpringIncubator.enums.BookStatus;
import com.example.JavaAndSpringIncubator.repositories.BooksRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService implements IBookService{

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

    public BookStatus createBook(BooksDTO newBook)
    {
        List<Books> books = booksRepository.findAll();
        for (Books book : books) {
            if (book.equals(newBook)) {
                return BookStatus.BOOK_ALREADY_EXISTS;
            }
        }

        booksRepository.save(newBook.toEntity());

        return BookStatus.SUCCESSFUL;
    }

    public BookStatus deleteBook(Integer bookID)
    {
        Optional<Books> books = booksRepository.findById(bookID);
        if(books.isPresent())
        {
            booksRepository.delete(books.get());
            return BookStatus.SUCCESSFUL;
        }
        else
        {
            return BookStatus.UNSUCCESSFUL;
        }
    }

    public BooksDTO updateBook(BooksDTO booksDTO, Integer bookID)
    {
        Optional<Books> book = booksRepository.findById(bookID);
        Books bookEntity = book.get();

        bookEntity.setTitle(booksDTO.getTitle());
        bookEntity.setAuthor(booksDTO.getAuthor());
        bookEntity.setVersion(booksDTO.getVersion());
        bookEntity.setDateOfPublication(booksDTO.getDateOfPublication());
        bookEntity.setNumberOfBooksInStock(booksDTO.getNumberOfBooksInStock());
        bookEntity.setPrice(booksDTO.getPrice());

        booksRepository.save(bookEntity);
        return booksDTO;
    }

}
