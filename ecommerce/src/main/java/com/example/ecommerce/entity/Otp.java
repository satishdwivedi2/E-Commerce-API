package com.example.ecommerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
public class Otp {
@Id 
@GeneratedValue 
 private int id;
 private String phoneNo;
 private String otp;
 private LocalDateTime expiryTime;

}
