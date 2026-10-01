package com.example.ecommerce.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Payment {
 @Id
 @GeneratedValue
 private int id;
 private int amount;
 private String paymentGateway;
 private String transactionId;
  @Enumerated(EnumType.STRING)
 private PaymentStatus status;
 @OneToOne
 @JoinColumn(name="order_id")
 private Order order; 
 private String razorpayOrderId;

}
