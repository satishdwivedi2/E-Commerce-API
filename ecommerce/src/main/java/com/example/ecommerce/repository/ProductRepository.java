package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer> {
  List<Product> findByNameContainingIgnoreCase(String name);
  List<Product> findByCategoryId(int id);
}
