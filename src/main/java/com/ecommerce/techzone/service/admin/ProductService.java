package com.ecommerce.techzone.service.admin;

import com.ecommerce.techzone.entity.Product;
import com.ecommerce.techzone.repository.admin.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
