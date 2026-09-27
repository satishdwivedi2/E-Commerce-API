package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/products")
@RequiredArgsConstructor 
public class ProductController {
  private final ProductService productService;

  @PostMapping
   public Product createProduct(@RequestBody Product product) {
       return productService.addProduct(product);
   }

   @GetMapping
   public List<Product> getAllProducts() {
       return productService.getAllProducts();
   }
  @GetMapping("/{id}")
   public Product getProductById(@PathVariable int id) {
       return productService.getProductById(id);
   }
   
   @PutMapping("/{id}")
   public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
       return productService.updateProduct(id, product);
   }
   @DeleteMapping ("/{id}")
   public void deleteProduct(@PathVariable int id) {
       productService.deleteProduct(id);
   }


}
