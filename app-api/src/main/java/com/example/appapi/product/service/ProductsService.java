package com.example.appapi.product.service;

import com.example.appapi.product.images.service.ProductsImagesService;
import com.example.appapi.product.model.Products;
import com.example.appapi.product.model.ProductsDto;
import com.example.appapi.product.repository.ProductsRepository;
import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.review.service.ProductReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

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
