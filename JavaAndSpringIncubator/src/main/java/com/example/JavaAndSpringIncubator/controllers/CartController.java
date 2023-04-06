package com.example.JavaAndSpringIncubator.controllers;

import com.example.JavaAndSpringIncubator.dto.*;
import com.example.JavaAndSpringIncubator.services.CartService;
import com.example.JavaAndSpringIncubator.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final ICartService iCartService;

    @Autowired
    public CartController(ICartService iCartService) {
        this.iCartService = iCartService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public ResponseEntity<List<CartDTO>> getCustomerCart() {
        List<CartDTO> customerCart = iCartService.getCustomerCart();

        return  ResponseEntity.ok(customerCart);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/addItem")
    public ResponseEntity<AddToCartDTO> addCartItem (@RequestBody AddToCartDTO addToCartDTO)
    {

        AddToCartDTO itemToAdd = iCartService.addCartItem(addToCartDTO);

        return new ResponseEntity<>(itemToAdd, HttpStatus.ACCEPTED);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/deleteItem/{cartItemID}")
    public ResponseEntity<CartItemDTO> deleteCartItem (@PathVariable Integer cartItemID)
    {

        CartItemDTO cartItem = iCartService.deleteCartItem(cartItemID);
        return new ResponseEntity<>(cartItem, HttpStatus.ACCEPTED);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PatchMapping("/editItem")
    public ResponseEntity<CartItemDTO> editCartItem (@RequestBody EditCartItemDTO editItem)
    {
        CartItemDTO updatedItem = iCartService.editCartItem(editItem);
        return new ResponseEntity<>(updatedItem, HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{cartID}")
    public ResponseEntity<CartDTO> getCartByID(@PathVariable Integer cartID)
    {
        CartDTO item = iCartService.getCartByID(cartID);
        return ResponseEntity.ok(item);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/item/{cartItemID}")
    public ResponseEntity<CartItemDTO> getCartItemByID(@PathVariable Integer cartItemID)
    {
        CartItemDTO item = iCartService.getCartItemByID(cartItemID);
        return ResponseEntity.ok(item);

    }
}
