package com.example.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public Category createCategory(Category category) {
     return categoryRepository.save(category);
  }
  public Category getCategoryById(int id) {
     return categoryRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
  }
  public Category updateCategory(int id, String name) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        existingCategory.setName(name);
     return categoryRepository.save(existingCategory);
  }
  public void deleteCategory(int id) {
    categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        categoryRepository.deleteById(id);
    }
   public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }



}
