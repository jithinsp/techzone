package com.ecommerce.techzone.repository.admin;

import com.ecommerce.techzone.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String firstName);

    Optional<User> findByUsernameIgnoreCaseLike(String firstName);
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE username LIKE :keyword% OR phone LIKE :keyword% OR phone LIKE :keyword% OR first_name LIKE :keyword% OR last_name LIKE :keyword% OR uuid like :keyword%", nativeQuery = true)
    Page<User> search(String keyword, Pageable pageable);
}
