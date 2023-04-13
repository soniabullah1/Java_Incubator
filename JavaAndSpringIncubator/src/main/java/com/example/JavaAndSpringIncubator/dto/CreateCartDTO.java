package com.example.JavaAndSpringIncubator.dto;

import com.example.JavaAndSpringIncubator.entities.CartItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class CreateCartDTO {

    private Integer cartID;
    private Integer userID;

    public CreateCartDTO() {
    }

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }


    public static CreateCartDTO fromEntity(com.example.JavaAndSpringIncubator.entities.Cart cartEntity) {
        CreateCartDTO createCartDTO = new CreateCartDTO();
        createCartDTO.cartID = cartEntity.getCartID();
        createCartDTO.userID = cartEntity.getUserID();
        return createCartDTO;
    }

    @JsonIgnore
    public com.example.JavaAndSpringIncubator.entities.Cart toEntity() {
        com.example.JavaAndSpringIncubator.entities.Cart cart = new com.example.JavaAndSpringIncubator.entities.Cart();
        cart.setCartID(getCartID());
        cart.setUserID(getUserID());
        return cart;
    }
}
