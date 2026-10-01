package com.example.ecommerce.dto;


import com.example.ecommerce.entity.PaymentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class PaymentResponseDto {
 private int id;
 private int amount;
 private String paymentGateway;
 private String transactionId;
 private PaymentStatus status;
 private String razorpayOrderId;
 private OrderResponseDto order;


}
