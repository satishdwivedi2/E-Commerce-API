package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class RazorpayStatusDto {
private String razorpayOrderId;
private String razorpayPaymentId;
private String razorpaySignature;
}
