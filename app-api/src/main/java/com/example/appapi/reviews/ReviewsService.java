package com.example.appapi.reviews;

import com.example.appapi.reviews.model.Reviews;
import com.example.appapi.reviews.model.ReviewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;

    public void register(ReviewsDto.Register dto) {
        reviewsRepository.save(dto.toEntity());
    }

    public List<Reviews> list() {
        List<Reviews> reviewList = reviewsRepository.findAll();
        return reviewList;
    }

    public ReviewsDto.Response read(Long idx) {
        Optional<Reviews> result = reviewsRepository.findById(idx);

        if(result.isPresent()) {
            Reviews reviews = result.get();
            return ReviewsDto.Response.from(reviews);
        }
        return null;
    }
}
