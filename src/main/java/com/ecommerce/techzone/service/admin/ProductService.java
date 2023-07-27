package com.ecommerce.techzone.service.admin;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.Product;
import com.ecommerce.techzone.repository.admin.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public void disableProduct(UUID product_id) {
        Product product=productRepository.findById(product_id).get();
        product.setIsdeleted(false);
        productRepository.save(product);
    }
    public void enableProduct(UUID product_id) {
        Product product=productRepository.findById(product_id).get();
        product.setIsdeleted(true);
        productRepository.save(product);
    }
}
