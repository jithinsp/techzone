package com.ecommerce.techzone.service;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.User;
import com.ecommerce.techzone.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "success";
    }

    public List<Category> searchCategory(String searchKey) {
        List<Category> category = categoryRepository
                .findBycategoryname(searchKey)
                .stream()
                .toList();
        return category;
    }
}
