package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
public class User {
    @Id
    @GeneratedValue 
    private int id;
    private String name;
    private String email;
    private String phoneNo;
    private String password;
    private boolean phoneVerified;
    @Enumerated (value = EnumType.STRING)
    private Role role;

   
    



}
