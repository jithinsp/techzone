package com.ecommerce.techzone.controller.admin;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    //To show add category page
    @GetMapping("category/addcategory")
    public String showAddCategoryPage(Model model){
        model.addAttribute("categoryObj",new Category());
        return "admin/addcategory";
    }

    //To add new category
    @PostMapping("/category/addCategory")
    public String addCategory(Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("category/search")
    public String categorySearch(@RequestParam String searchKey, Model model){
        model.addAttribute("category",categoryService.searchCategory(searchKey));
        return "/admin/admin_category";
    }

    @GetMapping("category/disable/{category_id}")
    public String blockCatgegory(@PathVariable UUID category_id){
        categoryService.disableCategory(category_id);
        return "redirect:/admin/category";
    }

    @GetMapping("category/enable/{category_id}")
    public String unblockCategory(@PathVariable UUID category_id){
        categoryService.enableCategory(category_id);
        return "redirect:/admin/category";
    }

}
