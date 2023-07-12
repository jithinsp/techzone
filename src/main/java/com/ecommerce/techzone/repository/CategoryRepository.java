package com.ecommerce.techzone.repository;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findBycategoryname(String categoryname);
}
