package com.ecommerce.techzone.repository.user;

import com.ecommerce.techzone.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public User findByEmail(String email);
    public User findByUsername(String username);
}
