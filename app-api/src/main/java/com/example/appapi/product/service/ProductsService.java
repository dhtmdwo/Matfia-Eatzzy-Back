package com.example.appapi.product.service;

import com.example.appapi.product.images.service.ProductsImagesService;
import com.example.appapi.product.model.Products;
import com.example.appapi.product.model.ProductsDto;
import com.example.appapi.product.repository.ProductsRepository;
import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.upload.PreSignedCloudImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductsImagesService productsImagesService;
    private final PreSignedCloudImageService preSignedCloudImageService;

    public ProductsDto.ProductRes preSigned(ProductsDto.Create dto) {
        // 상품 정보 DB에 저장
        Products product = productsRepository.save(dto.toEntity());

        List<String> uploadFilePaths = new ArrayList();
        List<String> preSignedUrls = new ArrayList();
        for (String file : dto.getImagePaths()) {
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
            String fileName = date + UUID.randomUUID() + "_" + file;
            String preSignedUrl = preSignedCloudImageService.upload(fileName, "image/png");
            preSignedUrls.add(preSignedUrl);
            uploadFilePaths.add(fileName);
        }
        // 이미지 저장 정보를 DB에 저장
        productsImagesService.create(uploadFilePaths, product);

        ProductsDto.ProductRes response =  ProductsDto.ProductRes.of(product);
        response.setImageUrls(preSignedUrls);

        return response;
    }


    // 리뷰 starPoint, 리뷰 개수, 이미지
    public List<ProductsDto.InfoResponse> list() {
        Page<Products> products = productsRepository.findAll(PageRequest.of(0, 6));
        return products.getContent().stream().map(ProductsDto.InfoResponse::fromEntity).collect(Collectors.toList());
    }

    public ProductsDto.DetailResponse getProduct(Long id) {
        Products products = productsRepository.findByIdxWithReviewsAndImages(id).orElseThrow();
        return ProductsDto.DetailResponse.fromEntity(products);
    }

    public List<ProductReviewsDto.InfoResponse> getProductReviews(Long idx) {
        return new ArrayList<>();
    }
}
