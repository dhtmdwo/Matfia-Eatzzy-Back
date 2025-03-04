package com.example.appapi.product.review.service;

import com.example.appapi.product.review.images.model.ProductReviewImages;
import com.example.appapi.product.review.model.ProductReviews;
import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.review.repository.ProductReviewsRepository;
import com.example.appapi.store.review.model.StoreReview;
import com.example.appapi.store.review.model.StoreReviewDto;
import com.example.appapi.store.review.model.StoreReviewImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReviewsService {
    private final ProductReviewsRepository productReviewsRepository;

    public int getStarPoint(Long idx) {
        ProductReviews productReviews = productReviewsRepository.findByIdx(idx).orElseThrow();
        return productReviews.getStarPoint();
    }

    public List<ProductReviewsDto.ProductReivewResponse> productList(Long idx) {
        List<ProductReviews> productReviews = productReviewsRepository.findReviewBy(idx);

        List<ProductReviewsDto.ProductReivewResponse> responseList = new ArrayList<>();

        for (ProductReviews productReview : productReviews) {
            List<ProductReviewImages> productReviewImageList = productReview.getImages();
            List <String> imageUrls = productReviewImageList.stream().map(ProductReviewImages::getImagePath).toList();
            ProductReviewsDto.ProductReivewResponse response = ProductReviewsDto.ProductReivewResponse.from(productReview, imageUrls);
            responseList.add(response);
        }

        return responseList;
    } // 마이페이지 클라이언트 상품 리뷰 보기

    public void deleteReview(Long idx) {
        productReviewsRepository.findById(idx).ifPresentOrElse(
                productReviewsRepository::delete,
                () -> {
                    throw new IllegalArgumentException("해당 식당이 존재하지 않습니다");
                }
        );// idx 값으로 검색후 있으면 삭제 없으면 예외처리
    } // 마이페이지 클라이언트 상품 리뷰 삭제
    

}
