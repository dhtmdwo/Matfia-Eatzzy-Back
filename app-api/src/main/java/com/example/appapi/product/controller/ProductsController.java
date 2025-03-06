package com.example.appapi.product.controller;

import com.example.appapi.product.model.ProductsDto;
import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.service.ProductsService;
import com.example.appapi.users.model.Users;
import com.example.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상품 기능")
@RestController
@RequestMapping("/app/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;

    @Operation(summary = "상품 등록", description = "상품을 등록하는 기능입니다.")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ProductsDto.ProductRes>> create(@AuthenticationPrincipal Users users, @RequestBody ProductsDto.Create request) {
        return ResponseEntity.ok(new BaseResponse(productsService.preSigned(request)));
    }

    @Operation(summary = "상품 리스트 조회", description = "상품 리스트를 조회하는 기능입니다.")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<ProductsDto.InfoResponse>>> getProducts() {
        return ResponseEntity.ok(new BaseResponse(productsService.list()));
    }

    @Operation(summary = "상품 단건 조회", description = "하나의 상품을 조회하는 기능입니다.")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ProductsDto.DetailResponse>> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(new BaseResponse(productsService.getProduct(id)));
    }

    @Operation(summary = "상품의 리뷰 조회", description = "상품의 리뷰를 조회하는 기능입니다.")
    @GetMapping("/{id}/reviews")
    public ResponseEntity<BaseResponse<List<ProductReviewsDto.InfoResponse>>> getProductReviews(@PathVariable Long id) {
        return ResponseEntity.ok(new BaseResponse(productsService.getProductReviews(id)));
    }
}
