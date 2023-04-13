package com.example.JavaAndSpringIncubator.dto;

import com.example.JavaAndSpringIncubator.entities.CartItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private Integer cartID;
    private Integer userID;
    List<CartItemDTO> cartItems;

    public CartDTO() {
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

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public static CartDTO fromEntity(com.example.JavaAndSpringIncubator.entities.Cart cartEntity) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.cartID = cartEntity.getCartID();
        cartDTO.userID = cartEntity.getUserID();
        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        for (CartItem cartItem : cartEntity.getCartItems())
        {
            cartItemDTOS.add(CartItemDTO.fromEntity(cartItem));
        }
        cartDTO.cartItems = cartItemDTOS;
        return cartDTO;
    }

    @JsonIgnore
    public com.example.JavaAndSpringIncubator.entities.Cart toEntity() {
       com.example.JavaAndSpringIncubator.entities.Cart cart = new com.example.JavaAndSpringIncubator.entities.Cart();
        cart.setCartID(getCartID());
        cart.setUserID(getUserID());
        List<CartItem> cartItems = new ArrayList<>();
        for (CartItemDTO cartItem : getCartItems())
        {
            CartItem tempCartItem = new CartItem();
            cartItems.add(cartItem.toEntity(tempCartItem));
        }
        cart.setCartItems(cartItems);
        return cart;
    }
}
