package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public CartItem addToCart(int userId, int productId, int quantity) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(
                "User not found with id: " + userId));

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException(
                "Product not found with id: " + productId));

        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        if (quantity > product.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        Optional<Cart> existingCart = cartRepository.findByUser(user);

        Cart cart = null;

        if (existingCart.isPresent()) {
            cart = existingCart.get();
        } else {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);
        CartItem cartItem = null;

        if (existingItem.isPresent()) {

            cartItem = existingItem.get();

            int newQuantity = cartItem.getQuantity() + quantity;

            if (newQuantity > product.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            cartItem.setQuantity(newQuantity);
            cartItemRepository.save(cartItem);

        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);

            cartItemRepository.save(cartItem);
        }

        return cartItem;
    }

    public List<CartItem> getAllItems(int userId) {
        Optional<User> existingUser = userRepository.findById(userId);
        User user = null;
        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            throw new RuntimeException("User not found with id" + userId);
        }
        Optional<Cart> existingCart = cartRepository.findByUser(user);
        Cart cart = null;
        if (existingCart.isPresent()) {
            cart = existingCart.get();
        } else {
            throw new RuntimeException("Cart not found with user" + userId);
        }
        List<CartItem> cartItem = cartItemRepository.findByCart(cart);
        return cartItem;
    }
    
    public CartItem updateCartItem(int userId,int productId,int quantity){
          User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(
                "User not found with id: " + userId));

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException(
                "Product not found with id: " + productId));

        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        if (quantity > product.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        Optional<Cart> existingCart = cartRepository.findByUser(user);

        Cart cart = null;

        if (existingCart.isPresent()) {
            cart = existingCart.get();
        } else {throw new RuntimeException("Cart not found");
        }

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);
        CartItem cartItem = null;

        if (existingItem.isPresent()) {

            cartItem = existingItem.get();

            if (quantity > product.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);

        } else {throw new RuntimeException("CartItem not found");
            
        }

        return cartItem;
    }

     public void removeCartItem(int userId,int productId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(
                "User not found with id: " + userId));

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException(
                "Product not found with id: " + productId));

        Optional<Cart> existingCart = cartRepository.findByUser(user);

        Cart cart = null;

        if (existingCart.isPresent()) {
            cart = existingCart.get();
        } else {throw new RuntimeException("Cart not found");
        }

        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart, product);
        CartItem cartItem = null;

        if (existingItem.isPresent()) {

            cartItem = existingItem.get();

        } else {throw new RuntimeException("CartItem not found");
            }
       cartItemRepository.delete(cartItem);
     }
    }

