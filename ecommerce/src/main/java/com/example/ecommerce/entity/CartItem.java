package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter
@NoArgsConstructor 
public class CartItem {
    @Id 
    @GeneratedValue 
    private int id;
    @Positive 
    private int quantity;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product; 
    @ManyToOne 
    @JoinColumn(name="cart_id")
    private Cart cart;


}
