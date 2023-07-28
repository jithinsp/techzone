package com.ecommerce.techzone.service.admin;

import com.ecommerce.techzone.entity.Image;
import com.ecommerce.techzone.repository.admin.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageReader;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
