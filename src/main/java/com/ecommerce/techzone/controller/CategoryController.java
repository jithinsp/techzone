package com.ecommerce.techzone.controller;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("category")
    public String categoryView(Model model){
        List<Category> category = categoryService.getCategory();
        model.addAttribute("category",category);
        return "admin/admin_category";
    }

    @PostMapping("addcategory")
    @ResponseBody
    public String addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("category/search")
    public String categorySearch(@RequestParam String searchKey, Model model){
        model.addAttribute("searchKey",categoryService.searchCategory(searchKey));
        return "/admin/admin_category";
    }

}
