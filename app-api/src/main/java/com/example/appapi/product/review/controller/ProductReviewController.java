package com.example.appapi.product.review.controller;

import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.review.service.ProductReviewsService;
import com.example.appapi.store.review.model.StoreReviewDto;
import com.example.appapi.users.model.Users;
import com.example.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products/reviews")
@RequiredArgsConstructor
public class ProductReviewController {
    private final ProductReviewsService productReviewsService;

    @Operation(summary = "작성한 상품 리뷰 보기(클라이언트)")
    @GetMapping("/mypage/store")
    public ResponseEntity<List<ProductReviewsDto.ProductReivewResponse>> storeList(@RequestParam("idx") Long idx) {
        List<ProductReviewsDto.ProductReivewResponse> responseList = productReviewsService.productList(idx);
        return ResponseEntity.ok(responseList);
    } // 마이페이지 클라이언트 식당 리뷰 보기

    @Operation(summary = "작성한 상품 리뷰 삭제(클라이언트)")
    @GetMapping("/mypage/productdelete")
    public ResponseEntity<String> deleteLikes(@RequestParam("idx") Long idx) {
        productReviewsService.deleteReview(idx);
        return ResponseEntity.ok("삭제 완료");
    } // 마이페이지 클라이언트 상품 리뷰 삭제


    @Operation(summary = "상품 리뷰 작성하기(클라이언트)")
    @PostMapping("/create")
    public ResponseEntity<ProductReviewsDto.ReviewRes> create(
            @RequestBody ProductReviewsDto.CreateReq dto) {
        ProductReviewsDto.ReviewRes response = productReviewsService.create(dto);
        return ResponseEntity.ok(response);
    } // 상품 리뷰 작성하기
    

}
