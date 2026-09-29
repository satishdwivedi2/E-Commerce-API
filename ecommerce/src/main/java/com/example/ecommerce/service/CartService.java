package com.example.ecommerce.service;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.User;

import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CartService {
  private final CartRepository cartRepository;
  private final UserRepository userRepository;


public Cart getCart(int userId){
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(
                "User not found with id: " + userId));
    Cart cart=cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException(
                "Cart not found with user: " + userId));
                return cart;
}
}
