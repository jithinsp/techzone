package com.ecommerce.techzone.user.controller;

import com.ecommerce.techzone.dto.CreateUserRequest;
import com.ecommerce.techzone.entity.User;
import com.ecommerce.techzone.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String userLogin(){
        return "user/user_login";
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
        if(!result){
            model.addAttribute("errorMsg","Email already exists");
            return "signup";
        }
        return "redirect:/signup";
        //paste home mapping when ready
    }

    @ResponseBody
    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(@Param("email") String email){
        return userService.isEmailUnique(email)? "OK" : "Duplicated";
    }

    @GetMapping("/")
    public String userHome(){
        return "user/user_home";
    }
}
