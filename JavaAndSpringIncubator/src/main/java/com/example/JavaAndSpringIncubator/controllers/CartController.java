package com.example.JavaAndSpringIncubator.controllers;

import com.example.JavaAndSpringIncubator.dto.AddToCartDTO;
import com.example.JavaAndSpringIncubator.dto.CartDTO;
import com.example.JavaAndSpringIncubator.dto.CartItemDTO;
import com.example.JavaAndSpringIncubator.entities.CartItem;
import com.example.JavaAndSpringIncubator.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<CartDTO>> getCustomerCart() {
        List<CartDTO> customerCart = cartService.getCustomerCart();

        return  ResponseEntity.ok(customerCart);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/addItem")
    public ResponseEntity<AddToCartDTO> addCartItem (@RequestBody AddToCartDTO addToCartDTO)
    {

        AddToCartDTO itemToAdd = cartService.addCartItem(addToCartDTO);

        return new ResponseEntity<>(itemToAdd, HttpStatus.ACCEPTED);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/deleteItem/{cartItemID}")
    public ResponseEntity<CartItemDTO> deleteCartItem (@PathVariable Integer cartItemID)
    {

        CartItemDTO cartItem = cartService.deleteCartItem(cartItemID);
        return new ResponseEntity<>(cartItem, HttpStatus.ACCEPTED);

    }
}
