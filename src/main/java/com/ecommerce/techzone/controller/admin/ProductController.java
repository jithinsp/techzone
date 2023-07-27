package com.ecommerce.techzone.controller.admin;

import com.ecommerce.techzone.entity.Product;
import com.ecommerce.techzone.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("product/disable/{product_id}")
    public String blockProduct(@PathVariable UUID product_id){
        productService.disableProduct(product_id);
        return "redirect:/admin/product";
    }

    @GetMapping("product/enable/{product_id}")
    public String unblockProduct(@PathVariable UUID product_id){
        productService.enableProduct(product_id);
        return "redirect:/admin/product";
    }
}
