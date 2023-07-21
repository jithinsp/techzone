package com.ecommerce.techzone.admin.service;

import com.ecommerce.techzone.entity.User;
import com.ecommerce.techzone.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public List<User> getAllUsers() {
        return adminRepository.findAll();
    }

    public String addUser(User user) {
        adminRepository.save(user);
        return "success";
    }

    public String deleteUser(Integer id) {
        adminRepository.deleteById(id);
        return "success";
    }

    public List<User> searchUser(String searchTerm) {
        List<User>users = adminRepository
                .findByfirstname(searchTerm)
                .stream()
                .toList();
        return users;
    }

//    public void blockUser(Integer user_id, boolean status) {
//        userRepository.updateActiveStatus(user_id,status);
//    }
}
