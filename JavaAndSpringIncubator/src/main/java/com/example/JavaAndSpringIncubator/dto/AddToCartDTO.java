package com.example.JavaAndSpringIncubator.dto;

import com.example.JavaAndSpringIncubator.entities.Books;
import com.example.JavaAndSpringIncubator.entities.CartItem;

public class AddToCartDTO {

    private Integer bookID;
    private Integer userID;
    private Integer quantity;

    public AddToCartDTO() {
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CartItem toEntity(Integer cartID) {
        CartItem cartItem = new CartItem();
        cartItem.setCartID(cartID);
        cartItem.setQuantity(quantity);
        Books books = new Books();
        books.setBookID(bookID);
        cartItem.setBookID(books);

        return  cartItem;
    }
}
