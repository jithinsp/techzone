package com.ecommerce.techzone.controller.user;

import com.ecommerce.techzone.dto.CreateUserRequest;
import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    public String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    //to show login page
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        System.out.println("inside login1");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            if (error != null) {
                model.addAttribute("error", "Invalid username or password");
            }
//            System.out.println(model);
            System.out.println("inside login2");
            return "user/user_login";
        }
        System.out.println("inside login3");
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, Model model){
        System.out.println("inside login post");
//        userService.findByEmail();
        if(!user.isVerified()){
            System.out.println("inside login to otp page");
            return "user/enterOtp";
        }
        System.out.println("inside login post 2");
        return "redirect:/";
    }

    //to show signup page
    @GetMapping("/signup")
    public String userSignup(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "user/user_signup";
    }

    //to signup
    @PostMapping("/users/save")
    public String saveUser(CreateUserRequest user, Model model){
        System.out.println(user);
        boolean result=userService.save(user);
        System.out.println(result);
        if(!result){
            model.addAttribute("errorMsg","User Already Exists!!!");
            return "user/user_signup";
        }
        return "redirect:/login";

    }


    @ResponseBody //to enable rest
    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(@Param("email") String email){
        System.out.println("in mail checking");
        return userService.isEmailUnique(email)? "OK" : "Duplicated";
    }

    @GetMapping("/")
    public String userHome(@AuthenticationPrincipal(expression = "firstname") String firstname, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("inside userhome");
//        boolean isAdmin = authentication.getAuthorities().stream()
//                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
//
//        if (isAdmin) {
//            System.out.println("admin");
//            return "redirect:/admin/admin_dashboard";
//        }
        model.addAttribute("firstname", firstname); //To display username on home page, also the params passed
        return "/user/user_home";
    }
}
