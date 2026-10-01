package com.example.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.OrderResponseDto;
import com.example.ecommerce.dto.OrderUserDto;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.OrderStatus;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final UserRepository userRepository;

  public OrderResponseDto createOrder(int userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(
        "User not found with id: " + userId));
    Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException(
        "Cart not found with user: " + userId));
    List<CartItem> cartItem = cartItemRepository.findByCart(cart);
    if (cartItem.isEmpty())
      throw new RuntimeException("Cart is Empty");
    Order order = new Order();
    order.setUser(user);
    order.setOrderStatus(OrderStatus.PENDING);
    int totalAmount = 0;
    for (CartItem s : cartItem) {
      Product product = s.getProduct();
      int quantity = s.getQuantity();
      int price = product.getPrice();
      totalAmount += price * quantity;
    }
    order.setTotalAmount(totalAmount);
    order = orderRepository.save(order);
    for (CartItem d : cartItem) {
      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setProduct(d.getProduct());
      orderItem.setPurchasePrice(d.getProduct().getPrice());
      orderItem.setQuantity(d.getQuantity());
      orderItemRepository.save(orderItem);
      order.getOrderItems().add(orderItem);
    }
    OrderResponseDto response = new OrderResponseDto();

    response.setId(order.getId());
    response.setOrderStatus(order.getOrderStatus());
    response.setTotalAmount(order.getTotalAmount());
    response.setOrderItems(order.getOrderItems());

    OrderUserDto userDto = new OrderUserDto();
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    userDto.setEmail(user.getEmail());
    userDto.setPhoneNo(user.getPhoneNo());

    response.setUser(userDto);

    return response;

  }

}
