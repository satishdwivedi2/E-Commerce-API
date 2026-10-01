package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.PaymentResponseDto;
import com.example.ecommerce.dto.RazorpayStatusDto;
import com.example.ecommerce.service.PaymentService;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;

@RequestMapping("/payments")
@RestController 
@RequiredArgsConstructor 
public class PaymentController {
  private final PaymentService paymentService;

  @PostMapping("/{orderId}")
  public PaymentResponseDto createPayment(@PathVariable int orderId) throws RazorpayException{
    return paymentService.createPayment(orderId);
  }
  @PostMapping ("/verify")
  public PaymentResponseDto verifyPayment(@RequestBody RazorpayStatusDto razorpayStatusDto) throws RazorpayException{
    return paymentService.verifyPayment(razorpayStatusDto);
}
}
