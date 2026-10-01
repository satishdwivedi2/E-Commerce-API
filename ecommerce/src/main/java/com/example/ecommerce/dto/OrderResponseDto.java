package com.example.ecommerce.dto;

import java.util.List;

import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
public class OrderResponseDto {
 private int id;
 private OrderStatus orderStatus;
 private int totalAmount;
 private OrderUserDto user;
 private List<OrderItem> orderItems;

}
