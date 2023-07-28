package com.ecommerce.techzone.repository.admin;

import com.ecommerce.techzone.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
