package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
public class Product {
    @Id 
    @GeneratedValue 
    private int id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    @ManyToOne 
    @JoinColumn(name="category_id")
    private Category category;



}
