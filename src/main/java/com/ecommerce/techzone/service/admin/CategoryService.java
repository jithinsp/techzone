package com.ecommerce.techzone.service.admin;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.Product;
import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.repository.admin.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Page<Category> getCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }


    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "success";
    }

    public Page<Category> searchCategory(String searchKey, Pageable pageable) {
        Page<Category> category = categoryRepository.findByNameContaining(searchKey, pageable);
        return category;
    }

    public void disableCategory(UUID category_id) {
        Category category=categoryRepository.findById(category_id).get();
        category.setIsdeleted(false);
        categoryRepository.save(category);
    }
    public void enableCategory(UUID category_id) {
        Category category=categoryRepository.findById(category_id).get();
        category.setIsdeleted(true);
        categoryRepository.save(category);
    }
    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }
}
