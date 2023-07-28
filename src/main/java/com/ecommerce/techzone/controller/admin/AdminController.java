package com.ecommerce.techzone.controller.admin;

import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.service.admin.AdminService;
import com.ecommerce.techzone.exception.UserNotFoundException;
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
@RequestMapping("/admin/getusers")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public String getAllUsers(Model model, @PageableDefault(size = 5) Pageable pageable){
        Page<User> users = adminService.getAllUsers(pageable);
        model.addAttribute("listUsers",users);
        return "admin/admin_user_management";
    }

    //To implement search
    @GetMapping("/search")
    public String searchUsersByUsername(@RequestParam String searchTerm,Model model, @PageableDefault(size = 5) Pageable pageable) {
        Page<User> users =adminService.searchUser(searchTerm, pageable);
        model.addAttribute("listUsers", users);
        return "admin/admin_user_management";
    }

//    @GetMapping("/search")
//    public String searchUsers(@RequestParam("searchTerm") String searchTerm, Model model, @PageableDefault(size = 10) Pageable pageable) {
//        Page<User> users = adminService.searchUsersByUsername(searchTerm, pageable);
//        model.addAttribute("users", users);
//        return "admin_user_list"; // Replace with your actual Thymeleaf template name for the user list.
//    }


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


//To edit user
//@GetMapping("/edit/{user_id}")
//public String editUser(@PathVariable(name = "user_id") UUID id , Model model){
//    try {
//        User user = adminService.get(id);
//        model.addAttribute("user",user);
//    } catch (UserNotFoundException exception) {
//        model.addAttribute("exception",exception.getMessage());
//        return "/admin/admin_editUser";
//    }
//    return "redirect:/getusers";
//}

//    @PostMapping("adduser")
//    @ResponseBody
//    public String insertUser(@RequestBody User user){
//        return adminService.addUser(user);
//    }

//    @GetMapping("/delete/{user_id}")
//    public String deleteUser(@PathVariable UUID user_id){
//        adminService.deleteUser(user_id);
//        return "redirect:/admin/getusers";
//    }

    @GetMapping("/block/{user_id}")
    public String blockUser(@PathVariable UUID user_id){
        adminService.blockUser(user_id);
        return "redirect:/admin/getusers";
    }

    @GetMapping("/unblock/{user_id}")
    public String unblockUser(@PathVariable UUID user_id){
        adminService.unblockUser(user_id);
        return "redirect:/admin/getusers";
    }

}