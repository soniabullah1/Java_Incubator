package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.*;
import com.example.JavaAndSpringIncubator.entities.Books;
import com.example.JavaAndSpringIncubator.entities.Cart;
import com.example.JavaAndSpringIncubator.entities.CartItem;
import com.example.JavaAndSpringIncubator.repositories.CartItemRepository;
import com.example.JavaAndSpringIncubator.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartDTO> getCustomerCart() {
        List<Cart> customerCartEntity = cartRepository.findAll();
        List<CartDTO> customerCartDTO = new ArrayList<>();

        for (int i = 0; i < customerCartEntity.size(); i++) {
            customerCartDTO.add(CartDTO.fromEntity(customerCartEntity.get(i)));
        }
        return  customerCartDTO;
    }

    public AddToCartDTO addCartItem (AddToCartDTO addToCartDTO)
    {
//        Cart cart = cartRepository.findByCustomerID(addToCartDTO.getCustomerID());

        Cart cart = cartRepository.findByCustomerID(1);

        cartItemRepository.save(addToCartDTO.toEntity(cart.getCartID()));

        return addToCartDTO;

    }

    public CartItemDTO deleteCartItem (Integer cartItemID)
    {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemID);
        if(cartItem.isPresent())
        {
            cartItemRepository.delete(cartItem.get());
            return CartItemDTO.fromEntity(cartItem.get());
        }

        return null;
    }

    public CartItemDTO editCartItem (EditCartItemDTO editItem)
    {
        Cart cart = cartRepository.findByCustomerID(editItem.getCustomerID());

        Optional<CartItem> cartItem = cartItemRepository.findById(editItem.getCartItemID());
        if(cartItem.isPresent())
        {
            cartItem.get().setQuantity(editItem.getQuantity());

            cartItemRepository.save(cartItem.get());
            return CartItemDTO.fromEntity(cartItem.get());
        }

        return null;
    }

    public CartDTO getCartByID(Integer cartID)
    {
        Optional<Cart> item = cartRepository.findById(cartID);

        return CartDTO.fromEntity(item.get());
    }

    public CartItemDTO getCartItemByID(Integer cartItemID)
    {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemID);
        if(cartItem.isPresent())
        {
            return CartItemDTO.fromEntity(cartItem.get());
        }

        return null;
    }
}
