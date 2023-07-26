package com.ecommerce.techzone.service.admin;

import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.repository.admin.AdminRepository;
import com.ecommerce.techzone.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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

    public String deleteUser(UUID id) {
        adminRepository.deleteById(id);
        return "success";
    }

    public List<User> searchUser(String searchTerm) {
        List<User>users = adminRepository
                .findByUsername(searchTerm)
                .stream()
                .toList();
        return users;
    }

    public Page<User> search(String keyword, Pageable pageable) {
        return adminRepository.search(keyword, pageable);
    }

    public User get(UUID id) throws UserNotFoundException {
        try {
            return adminRepository.findById(id).get();
        } catch (NoSuchElementException exception){
            throw new UserNotFoundException("Could not find any user with ID"+id);
        }
    }

    public void blockUser(UUID user_id) {
        User user=adminRepository.findById(user_id).get();
        user.setEnabled(false);
        adminRepository.save(user);
    }
    public void unblockUser(UUID user_id) {
        User user=adminRepository.findById(user_id).get();
        user.setEnabled(true);
        adminRepository.save(user);
    }
}
