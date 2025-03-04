package com.example.appapi.product.review.controller;

import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.review.service.ProductReviewsService;
import com.example.appapi.store.review.model.StoreReviewDto;
import com.example.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
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

}
