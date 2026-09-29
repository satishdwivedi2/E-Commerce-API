package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
  Optional<CartItem>findByCartAndProduct(Cart cart,Product product);
  List<CartItem>findByCart(Cart cart);

  
}