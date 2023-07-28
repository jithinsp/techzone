package com.ecommerce.techzone.repository.admin;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByNameContaining(String name, Pageable pageable);
}
