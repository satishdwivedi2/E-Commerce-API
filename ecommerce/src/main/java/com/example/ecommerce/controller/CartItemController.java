package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.service.CartItemService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("/cart-items/{userId}")
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping ("/{productId}/{quantity}")
    public CartItem addItem(@PathVariable int userId,
        @PathVariable int productId, @PathVariable int quantity){
        return cartItemService.addToCart(userId, productId, quantity);
    }
    @GetMapping
    public List<CartItem>  getItems(@PathVariable int userId){
        return cartItemService.getAllItems(userId);
    }
    @PutMapping("/{productId}/{quantity}")
    public CartItem updateItem(@PathVariable int userId,
        @PathVariable int productId, @PathVariable int quantity){
          return cartItemService.updateCartItem(userId, productId, quantity);
    }
    @DeleteMapping("/{productId}")
    public void deleteItem(@PathVariable int userId,
        @PathVariable int productId) {
        cartItemService.removeCartItem(userId,productId);
    }
}