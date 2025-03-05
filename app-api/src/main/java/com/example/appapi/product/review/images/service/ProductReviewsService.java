package com.example.appapi.product.review.images.service;

import com.example.appapi.product.review.images.model.ProductReviewImages;
import com.example.appapi.product.review.images.repository.ProductReviewImagesRepository;
import com.example.appapi.product.review.model.ProductReviews;
import com.example.appapi.product.review.repository.ProductReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductReviewsService {
    private final ProductReviewImagesRepository productReviewImagesRepository;

    @Value("${project.upload.path}")    //

    private String defaultUploadPath;

    private String makeDir() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String uploadPath = defaultUploadPath + "/" + date;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        return "/" + date;
    }

    public List<String> upload(MultipartFile[] files, ProductReviews productReviews) {
        List<String> uploadFilePaths = new ArrayList<>();
        String uploadPath = makeDir();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalFilename;
            String fullPath = uploadPath + "/" + fileName;
            uploadFilePaths.add(fullPath);

            File uploadFile = new File(defaultUploadPath + fullPath);
            try {
                file.transferTo(uploadFile);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 실패", e);
            }

            productReviewImagesRepository.save(ProductReviewImages.builder()
                    .imagePath(fullPath)
                    .productReviews(productReviews)
                    .build());
        }
        return uploadFilePaths;
    }
}
