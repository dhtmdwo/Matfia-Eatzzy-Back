package com.example.appapi.review;

import com.example.appapi.reviews.model.Reviews;
import com.example.appapi.reviews.model.ReviewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewsRepository reviewsRepository;

    public void register(ReviewsDto.Register dto) {
        reviewsRepository.save(dto.toEntity());
    }