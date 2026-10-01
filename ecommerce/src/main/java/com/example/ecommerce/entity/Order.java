package com.example.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders") 
@Getter 
@Setter
@NoArgsConstructor 
public class Order {
  @Id 
  @GeneratedValue 
  private int id;
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
  @Positive 
  private int totalAmount;
  @OneToMany (mappedBy="order")
  private List<OrderItem>orderItems= new ArrayList<>();
  @ManyToOne 
  @JoinColumn(name="user_id")
  private User user;

}
