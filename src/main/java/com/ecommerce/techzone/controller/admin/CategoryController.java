package com.ecommerce.techzone.controller.admin;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.Product;
import com.ecommerce.techzone.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/category")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String categoryView(Model model, @PageableDefault(size = 5) Pageable pageable){
        Page<Category> category = categoryService.getCategory(pageable);
        model.addAttribute("category",category);
        return "admin/admin_category";
    }

    //To show add category page
    @GetMapping("/addcategory")
    public String showAddCategoryPage(Model model){
        model.addAttribute("categoryObj",new Category());
        return "admin/addcategory";
    }

    //To add new category
    @PostMapping("/addCategory")
    public String addCategory(Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/search")
    public String categorySearch(@RequestParam String searchKey, Model model, @PageableDefault(size = 5) Pageable pageable){
        Page<Category> category = categoryService.searchCategory(searchKey, pageable);
        model.addAttribute("category",category);
        return "admin/admin_category";
    }

    @GetMapping("/disable/{category_id}")
    public String blockCatgegory(@PathVariable UUID category_id){
        categoryService.disableCategory(category_id);
        return "redirect:/admin/category";
    }

    @GetMapping("/enable/{category_id}")
    public String unblockCategory(@PathVariable UUID category_id){
        categoryService.enableCategory(category_id);
        return "redirect:/admin/category";
    }

    // Handler for displaying the Edit category form
    @GetMapping("/edit/{category_id}")
    public String editCategoryForm(@PathVariable UUID category_id, Model model) {
        Category category = categoryService.getCategoryById(category_id);
        model.addAttribute("category", category);
        return "admin/editcategory"; // Thymeleaf template for the edit form
    }

    // Handler for updating the category
    @PostMapping("/update")
    public String updateCategory(@ModelAttribute Category category) {
        categoryService.updateCategory(category);
        return "redirect:/admin/category"; // Redirect to the product listing page after update
    }
}


