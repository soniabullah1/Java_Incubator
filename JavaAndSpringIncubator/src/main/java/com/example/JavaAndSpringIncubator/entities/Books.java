package com.example.JavaAndSpringIncubator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Books")
@Getter
@Setter
public class Books {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bookID")
        private Integer bookID;

        @Column(name = "Title")
        private String title;

        @Column(name = "Author")
        private String author;

        @Column(name = "DateOfPublication")
        private LocalDate dateOfPublication;

        @Column(name = "Version")
        private Integer version;

        @Column(name = "NumberOfBooksInStock")
        private Integer numberOfBooksInStock;

        @Column(name = "Price")
        private BigDecimal price;

}
