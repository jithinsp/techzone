package com.ecommerce.techzone.controller;

import com.ecommerce.techzone.entity.User;
import com.ecommerce.techzone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("getusers")
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "admin/admin_user_management";
    }

    //To implement search
    @GetMapping("getusers/search")
    public String searchUsersByUsername(@RequestParam String searchTerm,Model model) {
        model.addAttribute("users",userService.searchUser(searchTerm));
        return "/admin/admin_user_management";
    }
//    @GetMapping("/adminpanel/edit/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String editUserForm(@PathVariable("id") Long id,Model model) {
//        User user=userRepository.findById(id).orElseThrow();
//        model.addAttribute("user", user);
//        return "updateuser";
//    }
//
//    @PostMapping("/adminpanel/update")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String updateUser(@ModelAttribute("user") User updatedUser) {
//        userService.upadateUser(updatedUser.getId(),updatedUser);
//        return "redirect:/adminpanel";
//    }

    @PostMapping("adduser")
    @ResponseBody
    public String insertUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("getusers/delete/{user_id}")
    public String deleteUser(@PathVariable Integer user_id){
        userService.deleteUser(user_id);
        return "redirect:/admin/getusers";
    }

//    @GetMapping("getusers/block/{user_id}/{status}")
//    public String blockUser(@PathVariable Integer user_id, boolean status){
//        userService.blockUser(user_id,status);
//        return "redirect:/admin/getusers";
//    }

}