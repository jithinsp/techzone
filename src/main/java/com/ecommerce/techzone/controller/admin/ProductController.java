package com.ecommerce.techzone.controller.admin;

import com.ecommerce.techzone.entity.Category;
import com.ecommerce.techzone.entity.Image;
import com.ecommerce.techzone.entity.Product;
import com.ecommerce.techzone.entity.user.User;
import com.ecommerce.techzone.service.admin.ImageService;
import com.ecommerce.techzone.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/product")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ImageService imageService;

    //To show add products page
    @GetMapping("/addproduct")
    public String showAddProductsPage(Model model){
        List<Category> categories = productService.getCategory();
        model.addAttribute("category", categories);
        model.addAttribute("products",new Product());
        return "admin/addproduct";
    }

    //To add new products
    @PostMapping("/addProduct")
    public String addProduct(Product product, @RequestParam UUID categoryId){
        productService.addProduct(product, categoryId);
        return "redirect:/admin/product";
    }

    //To upload image
//    @PostMapping("/uploadProductImage")
//    public String handleImageUpload(@RequestParam("imageFile") List<MultipartFile> imageFiles, @RequestParam("productId") UUID productId) {
//        Product product = productService.getProductById(productId);
//        List<Image> images = new ArrayList<>();
//        if(!imageFiles.get(0).getOriginalFilename().equals("")){
//            for (MultipartFile image : imageFiles) {
//                String fileLocation = handleFileUpload(image); // Save the image and get its file location
//                Image imageEntity = new Image(fileLocation,product); // Create an Image entity with the file location
//                imageEntity = imageService.save(imageEntity);
//                images.add(imageEntity); // Add the Image entity to the Product's list of images
//            }
//        }
//    }

//    private String handleFileUpload(MultipartFile file) throws IOException {
//        // Define the directory to save the file in
//        String rootPath = System.getProperty("user.dir");
//        String uploadDir = rootPath + "/src/main/resources/static/uploads";
//
//
//        // Create the directory if it doesn't exist
//        File dir = new File(uploadDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        // Generate a unique file name for the uploaded file
//        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
//
//        // Save the file to the upload directory
//        String filePath = uploadDir + "/" + fileName;
//        Path path = Paths.get(filePath);
//        System.out.println(path);
//        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//
//        // Return the file path
//        return fileName;
//    }


    @GetMapping("/search")
    public String productSearch(@RequestParam String searchKey, Model model, @PageableDefault(size = 5) Pageable pageable){
        Page<Product> products = productService.searchProduct(searchKey, pageable);
        model.addAttribute("product",products);
        return "admin/admin_products";
    }

    @GetMapping
    public String getProduct(Model model, @PageableDefault(size = 10) Pageable pageable){
        Page<Product> products = productService.getProducts(pageable);
        model.addAttribute("product", products);
        return "/admin/admin_products";
    }

    @GetMapping("/disable/{product_id}")
    public String blockProduct(@PathVariable UUID product_id){
        productService.disableProduct(product_id);
        return "redirect:/admin/product";
    }

    @GetMapping("/enable/{product_id}")
    public String unblockProduct(@PathVariable UUID product_id){
        productService.enableProduct(product_id);
        return "redirect:/admin/product";
    }

    // Handler for displaying the Edit Product form
    @GetMapping("/edit/{product_id}")
    public String editProductForm(@PathVariable UUID product_id, Model model) {
        Product product = productService.getProductById(product_id);
        model.addAttribute("product", product);
        return "admin/editproduct"; // Thymeleaf template for the edit form
    }

    // Handler for updating the product
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/admin/product"; // Redirect to the product listing page after update
    }
}
