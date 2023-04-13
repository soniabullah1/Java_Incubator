package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.*;
import com.example.JavaAndSpringIncubator.entities.Cart;
import com.example.JavaAndSpringIncubator.entities.CartItem;
import com.example.JavaAndSpringIncubator.enums.CartStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ICartService {

    List<CartDTO> getCustomerCart();

    AddToCartDTO addCartItem (AddToCartDTO addToCartDTO);

    CartItemDTO deleteCartItem (Integer cartItemID);

    CartItemDTO editCartItem (EditCartItemDTO editItem);

    CartDTO getCartByUsersID(Integer cartID);

    CartItemDTO getCartItemByID(Integer cartItemID);

    CartStatus createCartForUser(Integer customerID, CreateCartDTO cartDTO);
}
