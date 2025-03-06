package com.example.appapi.product.review.service;

import com.example.appapi.product.model.Products;
import com.example.appapi.product.repository.ProductsRepository;
import com.example.appapi.product.review.images.model.ProductReviewImages;
import com.example.appapi.product.review.images.service.ProductReviewsImagesService;
import com.example.appapi.product.review.model.ProductReviews;
import com.example.appapi.product.review.model.ProductReviewsDto;
import com.example.appapi.product.review.repository.ProductReviewsRepository;
import com.example.appapi.store.model.Store;
import com.example.appapi.store.review.model.StoreReview;
import com.example.appapi.store.review.model.StoreReviewDto;
import com.example.appapi.store.review.model.StoreReviewImage;
import com.example.appapi.store.review.repository.StoreReviewRepository;
import com.example.appapi.store.review.service.StoreReviewImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReviewsService {
    private final ProductReviewsRepository productReviewsRepository;
    private final ProductReviewsImagesService productReviewsImagesService;
    private final ProductsRepository productsRepository;

    public ProductReviewsDto.ReviewRes create(ProductReviewsDto.CreateReq dto) {
        Products products = productsRepository.findById(dto.getProductIdx()).orElseThrow(); //product entity 검색

        ProductReviews productReviews = productReviewsRepository.save(dto.toEntity(products)); // review 테이블에 저장

        return productReviewsImagesService.preSigned(dto,productReviews); // 업로드와 imageReview 테이블 저장
    } // 상품 리뷰 작성하기

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
