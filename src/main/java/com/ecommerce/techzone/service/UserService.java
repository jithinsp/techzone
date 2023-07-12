package com.ecommerce.techzone.service;

import com.ecommerce.techzone.entity.User;
import com.ecommerce.techzone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        userRepository.save(user);
        return "success";
    }

    public String deleteUser(Integer id) {
        userRepository.deleteById(id);
        return "success";
    }

    public List<User> searchUser(String searchTerm) {
        List<User>users = userRepository
                .findByfirstname(searchTerm)
                .stream()
                .toList();
        return users;
    }

//    public void blockUser(Integer user_id, boolean status) {
//        userRepository.updateActiveStatus(user_id,status);
//    }
}
