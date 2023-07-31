package com.ecommerce.techzone.controller.user;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.Product;
import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.service.admin.CategoryService;
import com.ecommerce.techzone.service.admin.ProductService;
import com.ecommerce.techzone.service.user.OtpService;
import com.ecommerce.techzone.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    OtpService otpService;

//    @GetMapping("/")
//    public String userHome(
//            @AuthenticationPrincipal(expression = "firstname") String firstname,
//            Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("inside userhome");
//        boolean isAdmin = authentication.getAuthorities().stream()
//                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
//
//        if (isAdmin) {
//            System.out.println("admin");
//            return "redirect:/admin/admin_dashboard";
//        }
//        model.addAttribute("firstname", firstname); //To display username on home page, also the params passed
//        return "/user/user_home";
//    }

    @GetMapping("/")
    public String View(Model model,
                       @PageableDefault(size = 4) Pageable pageable,
                       @AuthenticationPrincipal(expression = "username") String username){
        System.out.println( " username:" + username);
        User newUser =userService.findByUsername(username);
        if(!newUser.isVerified()){
            System.out.println("inside login to otp page");
            model.addAttribute("username", username);
            return "redirect:/otp/enterOtp";
        }
        model.addAttribute("username", username);
        Page<Category> category = categoryService.getCategory(pageable);
        model.addAttribute("category",category);
        Page<Product> product = productService.getProducts(pageable);
        model.addAttribute("product",product);
        if(username.equals("admin")){
            return "/admin/admin_dashboard";
        }
        return "index";
    }

//    @GetMapping("/enterOtp")
//    public String showOtp(){
//        return "user/enterOtp";
//    }
//    @GetMapping("/search")
//    public String Search(@RequestParam String searchKey,
//                                 Model model,
//                                 @PageableDefault(size = 5) Pageable pageable){
//        Page<Product> product = productService.searchProduct(searchKey, pageable);
//        Page<Category> category = categoryService.searchCategory(searchKey, pageable);
//        model.addAttribute("category",category);
//        model.addAttribute("product",product);
//        return "admin/admin_category";
//    }
}
