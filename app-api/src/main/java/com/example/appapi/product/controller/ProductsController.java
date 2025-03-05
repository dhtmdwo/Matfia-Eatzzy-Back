package com.example.appapi.product.controller;

import com.example.appapi.product.model.ProductsDto;
import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.service.ProductsService;
import com.example.appapi.users.model.Users;
import com.example.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;


    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ProductsDto.ProductRes>> create(@AuthenticationPrincipal Users users, @RequestBody ProductsDto.Create request) {
        return ResponseEntity.ok(new BaseResponse(productsService.preSigned(request)));
    }

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
