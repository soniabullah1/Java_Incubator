package com.example.JavaAndSpringIncubator.dto;

import com.example.JavaAndSpringIncubator.entities.Books;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BooksDTO {

    private Integer bookID;
    private String title;
    private String author;
    private LocalDate dateOfPublication;
    private Integer version;
    private Integer numberOfBooksInStock;
    private BigDecimal price;

    public BooksDTO() {
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getNumberOfBooksInStock() {
        return numberOfBooksInStock;
    }

    public void setNumberOfBooksInStock(Integer numberOfBooksInStock) {
        this.numberOfBooksInStock = numberOfBooksInStock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static BooksDTO fromEntity(com.example.JavaAndSpringIncubator.entities.Books booksEntity) {
        BooksDTO booksDTO = new BooksDTO();
        booksDTO.bookID = booksEntity.getBookID();
        booksDTO.title = booksEntity.getTitle();
        booksDTO.author = booksEntity.getAuthor();
        booksDTO.dateOfPublication = booksEntity.getDateOfPublication();
        booksDTO.version = booksEntity.getVersion();
        booksDTO.numberOfBooksInStock = booksEntity.getNumberOfBooksInStock();
        booksDTO.price = booksEntity.getPrice();
        return booksDTO;
    }

    @JsonIgnore
    public com.example.JavaAndSpringIncubator.entities.Books toEntity() {
        com.example.JavaAndSpringIncubator.entities.Books books = new com.example.JavaAndSpringIncubator.entities.Books();
        books.setBookID(getBookID());
        books.setTitle(getTitle());
        books.setAuthor(getAuthor());
        books.setDateOfPublication(getDateOfPublication());
        books.setVersion(getVersion());
        books.setNumberOfBooksInStock(getNumberOfBooksInStock());
        books.setPrice(getPrice());
        return books;
    }

    public static List<BooksDTO> toDtos(List<Books> booksEntity)
    {
        List<BooksDTO> booksDTO = new ArrayList<>();
        for(Books book : booksEntity)
        {
            booksDTO.add(BooksDTO.fromEntity(book));
        }

        return booksDTO;
    }
}
