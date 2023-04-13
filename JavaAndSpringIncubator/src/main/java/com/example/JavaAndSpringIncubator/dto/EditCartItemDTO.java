package com.example.JavaAndSpringIncubator.dto;

public class EditCartItemDTO {

    private Integer userID;
    private Integer quantity;
    private Integer cartItemID;

    public EditCartItemDTO() {
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

    public Integer getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(Integer cartItemID) {
        this.cartItemID = cartItemID;
    }

}
