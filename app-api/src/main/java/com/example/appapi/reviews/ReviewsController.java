package com.example.appapi.reviews;

import com.example.appapi.reviews.model.Reviews;
import com.example.appapi.reviews.model.ReviewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ReviewsDto.Register dto) {
        reviewsService.register(dto);
        return ResponseEntity.ok("성공");
    }
}