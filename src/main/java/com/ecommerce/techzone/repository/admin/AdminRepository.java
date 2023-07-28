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

    Page<User> findByUsername(String firstName, Pageable pageable);
    Optional<User> findByUsername(String firstName);

    Page<User> findByUsernameIgnoreCaseLike(String firstName, Pageable pageable);
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE username LIKE :keyword% OR phone LIKE :keyword% OR phone LIKE :keyword% OR firstname LIKE :keyword% OR lastname LIKE :keyword% OR user_id like :keyword%", nativeQuery = true)
    Page<User> search(String keyword, Pageable pageable);
}
