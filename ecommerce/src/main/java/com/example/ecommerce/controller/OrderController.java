package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
public class OrderController {
  private final OrderService orderService;

  @PostMapping("/orders/{userId}")
  public OrderResponseDto creatOrder(@PathVariable int userId){
    return orderService.createOrder(userId);
  }

}
