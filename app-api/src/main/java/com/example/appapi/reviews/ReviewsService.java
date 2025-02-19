package com.example.appapi.reviews;

import com.example.appapi.reviews.model.ReviewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;

    public void register(ReviewsDto.Register dto) {
        reviewsRepository.save(dto.toEntity());
    }
}