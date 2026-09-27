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

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/categories")
@RestController 
@RequiredArgsConstructor 
public class CategoryController {
   private final CategoryService categoryService;

   @PostMapping
   public Category createCategory(@RequestBody Category category) {
       return categoryService.createCategory(category);
   }

   @GetMapping
   public List<Category> getAllCategories() {
       return categoryService.getAllCategories();
   }
  @GetMapping("/{id}")
   public Category getCategoryById(@PathVariable int id) {
       return categoryService.getCategoryById(id);
   }
   
   @PutMapping("/{id}")
   public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
       return categoryService.updateCategory(id, category.getName());
   }
   @DeleteMapping ("/{id}")
   public void deleteCategory(@PathVariable int id) {
       categoryService.deleteCategory(id);
   }


}
