package com.example.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.Order;





public interface PaymentRepository extends JpaRepository<Payment,Integer>{
    Optional<Payment> findByOrder(Order order);
    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);
}
