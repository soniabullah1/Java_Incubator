package com.example.JavaAndSpringIncubator.dto;

public class EditCartItemDTO {

    private Integer customerID;
    private Integer quantity;
    private Integer cartItemID;

    public EditCartItemDTO() {
    }


    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
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
