package com.example.JavaAndSpringIncubator.dto;

import com.example.JavaAndSpringIncubator.entities.Books;
import com.example.JavaAndSpringIncubator.entities.CartItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CartItemDTO {

    private Integer cartItemID;
    private Integer customerCartID;

    private Integer bookID;

    private String title;

    private String author;

    private LocalDate dateOfPublication;

    private Integer version;

    private Integer numberOfBooksInstock;
    private BigDecimal price;

    public CartItemDTO() {
    }

    public Integer getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(Integer cartItemID) {
        this.cartItemID = cartItemID;
    }

    public Integer getCustomerCartID() {
        return customerCartID;
    }

    public void setCustomerCartID(Integer customerCartID) {
        this.customerCartID = customerCartID;
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

    public Integer getNumberOfBooksInstock() {
        return numberOfBooksInstock;
    }

    public void setNumberOfBooksInstock(Integer numberOfBooksInstock) {
        this.numberOfBooksInstock = numberOfBooksInstock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static CartItemDTO fromEntity(CartItem cartItemEntity) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.cartItemID = cartItemEntity.getCartItemID();
        cartItemDTO.customerCartID = cartItemEntity.getCartID();
        cartItemDTO.bookID = cartItemEntity.getBookID().getBookID();
        cartItemDTO.title = cartItemEntity.getBookID().getTitle();
        cartItemDTO.author = cartItemEntity.getBookID().getAuthor();
        cartItemDTO.dateOfPublication = cartItemEntity.getBookID().getDateOfPublication();
        cartItemDTO.version = cartItemEntity.getBookID().getVersion();
        cartItemDTO.numberOfBooksInstock = cartItemEntity.getBookID().getNumberOfBooksInStock();
        cartItemDTO.price = cartItemEntity.getBookID().getPrice();


        return cartItemDTO;
    }

    @JsonIgnore
    public com.example.JavaAndSpringIncubator.entities.CartItem toEntity(CartItem cartItem) {

        cartItem.setCartItemID(getCartItemID());
        cartItem.setCartItemID(getCustomerCartID());
        Books temp = new Books();
        cartItem.setBookID(temp);
        cartItem.getBookID().setTitle(title);
        cartItem.getBookID().setAuthor(author);
        cartItem.getBookID().setDateOfPublication(dateOfPublication);
        cartItem.getBookID().setVersion(version);
        cartItem.getBookID().setNumberOfBooksInStock(numberOfBooksInstock);
        cartItem.getBookID().setPrice(new BigDecimal(99999));

        cartItem.getBookID().setBookID(bookID);


        return cartItem;
    }
}
