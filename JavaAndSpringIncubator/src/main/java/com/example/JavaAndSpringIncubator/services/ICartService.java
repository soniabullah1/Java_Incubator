package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.AddToCartDTO;
import com.example.JavaAndSpringIncubator.dto.CartDTO;
import com.example.JavaAndSpringIncubator.dto.CartItemDTO;
import com.example.JavaAndSpringIncubator.dto.EditCartItemDTO;
import com.example.JavaAndSpringIncubator.entities.Cart;
import com.example.JavaAndSpringIncubator.entities.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ICartService {

    List<CartDTO> getCustomerCart();

    AddToCartDTO addCartItem (AddToCartDTO addToCartDTO);

    CartItemDTO deleteCartItem (Integer cartItemID);

    CartItemDTO editCartItem (EditCartItemDTO editItem);

    CartDTO getCartByID(Integer cartID);

    CartItemDTO getCartItemByID(Integer cartItemID);
}
