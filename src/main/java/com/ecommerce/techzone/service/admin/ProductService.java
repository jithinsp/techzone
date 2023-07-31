package com.ecommerce.techzone.service.admin;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.Product;
import com.ecommerce.techzone.repository.admin.CategoryRepository;
import com.ecommerce.techzone.repository.admin.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Product addProduct(Product product, UUID categoryId) {

        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new NotFoundException("Category not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Page<Product> searchProduct(String searchKey, Pageable pageable) {
        Page<Product> product = productRepository.findByNameContaining(searchKey, pageable);
        return product;
    }
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Category> getCategory() {
        return categoryRepository.findAll();
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

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
