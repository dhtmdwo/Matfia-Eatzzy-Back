package com.example.appapi.product.review.service;

import com.example.appapi.product.review.model.ProductReviews;
import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.review.repository.ProductReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReviewsService {
    private final ProductReviewsRepository productReviewsRepository;

    public int getStarPoint(Long idx) {
        ProductReviews productReviews = productReviewsRepository.findByIdx(idx).orElseThrow();
        return productReviews.getStarPoint();
    }

}
