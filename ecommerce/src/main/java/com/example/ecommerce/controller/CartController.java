package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
public class CartController {
  private final CartService cartService;
  @GetMapping("/cart/{userId}")
  public Cart getCart(@PathVariable int userId){
    return cartService.getCart(userId);
  }




}
