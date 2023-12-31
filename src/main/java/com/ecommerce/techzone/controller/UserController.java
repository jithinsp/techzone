package com.ecommerce.techzone.controller;

import com.ecommerce.techzone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user_login")
    public String userLogin(){
        return "user/user_login";
    }

    @GetMapping("/user_signup")
    public String userSignup(){
        return "user/user_signup";
    }
    @GetMapping("/")
    public String userHome(){
        return "user/user_home";
    }
}
