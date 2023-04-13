package com.example.JavaAndSpringIncubator.services;

import com.example.JavaAndSpringIncubator.dto.*;
import com.example.JavaAndSpringIncubator.entities.Cart;
import com.example.JavaAndSpringIncubator.entities.CartItem;
import com.example.JavaAndSpringIncubator.enums.CartStatus;
import com.example.JavaAndSpringIncubator.repositories.CartItemRepository;
import com.example.JavaAndSpringIncubator.repositories.CartRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService implements ICartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    Logger logger = LogManager.getLogger(CartService.class.getName());

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
//        logger.info("testinggg: " + addToCartDTO.getUserID());
        Cart cart = cartRepository.findByUserID(addToCartDTO.getUserID());

//        Cart cart = cartRepository.findByUserID(1);

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
        Cart cart = cartRepository.findByUserID(editItem.getUserID());

        Optional<CartItem> cartItem = cartItemRepository.findById(editItem.getCartItemID());
        if(cartItem.isPresent())
        {
            cartItem.get().setQuantity(editItem.getQuantity());

            cartItemRepository.save(cartItem.get());
            return CartItemDTO.fromEntity(cartItem.get());
        }

        return null;
    }

    public CartDTO getCartByUsersID(Integer cartID)
    {
//        Optional<Cart> item = cartRepository.findById(cartID);
        Cart cart = cartRepository.findByUserID(cartID);

        return CartDTO.fromEntity(cart);
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

    public CartStatus createCartForUser(Integer customerID, CreateCartDTO cartDTO)
    {
        List<Cart> cart = cartRepository.findAll();
        for (Cart customerCart : cart) {
            if (customerCart.equals(cartDTO)) {
                return CartStatus.CART_ALREADY_EXISTS;
            }
        }

        // Set the customer ID for the cart object before saving it
        cartDTO.setUserID(customerID);

        cartRepository.save(cartDTO.toEntity());

        return CartStatus.SUCCESSFUL;
    }

}
