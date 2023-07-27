package com.ecommerce.techzone.service.admin;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.repository.admin.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
                .findByNameLike(searchKey)
                .stream()
                .toList();
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
}
