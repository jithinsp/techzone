package com.ecommerce.techzone.admin.repository;

import com.ecommerce.techzone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByfirstname(String firstname);
//    @Modifying
//    @Query("UPDATE User u SET u.active = :active WHERE u.id = :userId")
//    void updateActiveStatus(@Param("user_id") Integer user_id, @Param("active")boolean active);
}
