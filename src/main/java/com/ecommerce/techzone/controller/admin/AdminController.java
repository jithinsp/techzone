package com.ecommerce.techzone.controller.admin;

import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.service.admin.AdminService;
import com.ecommerce.techzone.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("getusers")
    public String getAllUsers(Model model){
        List<User> users = adminService.getAllUsers();
        model.addAttribute("listUsers",users);
        return "admin/admin_user_management";
    }

    //To implement search
    @GetMapping("getusers/search")
    public String searchUsersByUsername(@RequestParam String searchTerm,Model model) {
        model.addAttribute("users", adminService.searchUser(searchTerm));
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


//To edit user
//@GetMapping("/getusers/edit/{user_id}")
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

//    @GetMapping("getusers/delete/{user_id}")
//    public String deleteUser(@PathVariable UUID user_id){
//        adminService.deleteUser(user_id);
//        return "redirect:/admin/getusers";
//    }

    @GetMapping("getusers/block/{user_id}")
    public String blockUser(@PathVariable UUID user_id){
        adminService.blockUser(user_id);
        return "redirect:/admin/getusers";
    }

    @GetMapping("getusers/unblock/{user_id}")
    public String unblockUser(@PathVariable UUID user_id){
        adminService.unblockUser(user_id);
        return "redirect:/admin/getusers";
    }

}