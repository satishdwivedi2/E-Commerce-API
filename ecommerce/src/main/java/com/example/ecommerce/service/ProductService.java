package com.example.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class ProductService {
  private final ProductRepository productRepository;

  public Product addProduct(Product product){
    return productRepository.save(product);
  }
  public List<Product>getAllProducts(){
    return productRepository.findAll();
  }
  public Product getProductById(int id) {
     return productRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
  }
  public Product updateProduct(int id, Product product) {
        Product d = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        d.setName(product.getName());
        d.setDescription(product.getDescription());
        d.setPrice(product.getPrice());
        d.setQuantity(product.getQuantity());
        d.setCategory(product.getCategory());
     return productRepository.save(d);
  }
  public void deleteProduct(int id) {
    productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.deleteById(id);
    } 


}
