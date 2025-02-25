package com.example.appapi.product.controller;

import com.example.appapi.product.model.ProductsDto;
import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.service.ProductsService;
import com.example.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<ProductsDto.InfoResponse>>> getProducts() {
        return ResponseEntity.ok(new BaseResponse(productsService.list()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductsDto.DetailResponse>> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(new BaseResponse(productsService.getProduct(id)));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse<List<ProductReviewsDto.InfoResponse>>> getProductReviews(@PathVariable Long id) {
        return ResponseEntity.ok(new BaseResponse(productsService.getProductReviews(id)));
    }
}
