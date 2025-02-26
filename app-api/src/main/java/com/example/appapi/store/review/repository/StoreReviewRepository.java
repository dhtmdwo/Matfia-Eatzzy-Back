package com.example.appapi.store.review.repository;

import com.example.appapi.store.review.model.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {

}