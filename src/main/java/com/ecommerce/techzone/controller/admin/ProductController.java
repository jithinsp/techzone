package com.ecommerce.techzone.controller.admin;

import com.ecommerce.techzone.entity.Product;
import com.ecommerce.techzone.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("product")
    public String getProduct(Model model){
        List<Product> products =  productService.getProducts();
        model.addAttribute("product", products);
        return "/admin/admin_products";
    }
}
