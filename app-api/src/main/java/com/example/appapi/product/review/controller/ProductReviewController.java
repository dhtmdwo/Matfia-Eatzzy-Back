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

import java.util.List;

@RestController
@RequestMapping("/products/reviews")
@RequiredArgsConstructor
public class ProductReviewController {
    private final ProductReviewsService productReviewsService;

    @GetMapping("/mypage/store")
    public ResponseEntity<List<ProductReviewsDto.ProductReivewResponse>> storeList(@RequestParam("idx") Long idx) {
        List<ProductReviewsDto.ProductReivewResponse> responseList = productReviewsService.productList(idx);
        return ResponseEntity.ok(responseList);
    } // 마이페이지 클라이언트 식당 리뷰 보기

    @GetMapping("/mypage/productdelete")
    public ResponseEntity<String> deleteLikes(@RequestParam("idx") Long idx) {
        productReviewsService.deleteReview(idx);
        return ResponseEntity.ok("삭제 완료");
    } // 마이페이지 클라이언트 상품 리뷰 삭제

    @Operation(summary = "상품 리뷰 등록", description = "상품의 리뷰를 등록하는 기능입니다.")
    @PostMapping("/register")
    public ResponseEntity<String> registerReview(@RequestBody ProductReviewsDto.RegisterRequest request, @AuthenticationPrincipal Users users) {
        productReviewsService.save(request,users);
        return ResponseEntity.ok("ok");
    }
}
