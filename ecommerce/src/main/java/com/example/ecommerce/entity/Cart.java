package com.example.ecommerce.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
public class Cart {
    @Id 
    @GeneratedValue 
    private int id;
    @OneToOne 
    @JoinColumn(name="user_id")
    private User user;
    

}
