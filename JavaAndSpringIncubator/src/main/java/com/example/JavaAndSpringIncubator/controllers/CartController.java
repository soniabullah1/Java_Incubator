package com.example.JavaAndSpringIncubator.controllers;

import com.example.JavaAndSpringIncubator.dto.AddToCartDTO;
import com.example.JavaAndSpringIncubator.dto.CartDTO;
import com.example.JavaAndSpringIncubator.entities.Cart;
import com.example.JavaAndSpringIncubator.repositories.CartItemRepository;
import com.example.JavaAndSpringIncubator.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final CartItemRepository cartItemRepository;

    public CartController(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<CartDTO>> getCustomerCart() {
        List<Cart> customerCartEntity = cartRepository.findAll();
        List<CartDTO> customerCartDTO = new ArrayList<>();

        for (int i = 0; i < customerCartEntity.size(); i++) {
            customerCartDTO.add(CartDTO.fromEntity(customerCartEntity.get(i)));
        }
        return  ResponseEntity.ok(customerCartDTO);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/addItem")
    public ResponseEntity<AddToCartDTO> addCartItem (@RequestBody AddToCartDTO addToCartDTO)
    {
//        Cart cart = cartRepository.findByCustomerID(addToCartDTO.getCustomerID());

        Cart cart = cartRepository.findByCustomerID(1);

        cartItemRepository.save(addToCartDTO.toEntity(cart.getCartID()));

        return new ResponseEntity<>(addToCartDTO, HttpStatus.ACCEPTED);

    }
}
