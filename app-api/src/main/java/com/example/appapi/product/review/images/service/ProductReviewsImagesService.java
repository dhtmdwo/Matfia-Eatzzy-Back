package com.example.appapi.product.review.images.service;

import com.example.appapi.product.images.model.ProductsImages;
import com.example.appapi.product.model.Products;
import com.example.appapi.product.model.ProductsDto;
import com.example.appapi.product.review.images.model.ProductReviewImages;
import com.example.appapi.product.review.images.repository.ProductReviewImagesRepository;
import com.example.appapi.product.review.model.ProductReviews;
import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.service.ProductsService;
import com.example.appapi.upload.PreSignedCloudImageService;
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
public class ProductReviewsImagesService {
    private final ProductReviewImagesRepository productReviewImagesRepository;
    private final PreSignedCloudImageService preSignedCloudImageService;

    public ProductReviewsDto.ReviewRes preSigned(ProductReviewsDto.CreateReq dto,ProductReviews productReviews) {

        List<String> uploadFilePaths = new ArrayList();
        List<String> preSignedUrls = new ArrayList();
        for (String file : dto.getImageUrls()) {
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
            String fileName = date + UUID.randomUUID() + "_" + file;
            String preSignedUrl = preSignedCloudImageService.upload(fileName, "image/png");
            preSignedUrls.add(preSignedUrl);
            uploadFilePaths.add(fileName);
        }
        // 이미지 저장 정보를 DB에 저장
        create(uploadFilePaths, productReviews);

        return ProductReviewsDto.ReviewRes.of(productReviews,preSignedUrls);
    }


    public void create(List<String> uploadFilePaths, ProductReviews productReviews) {
        for(String uploadFilePath: uploadFilePaths) {
            productReviewImagesRepository.save(
                    ProductReviewImages.builder()
                            .imagePath(uploadFilePath)
                            .productReviews(productReviews)
                            .build())
            ;
        }
    }
}
