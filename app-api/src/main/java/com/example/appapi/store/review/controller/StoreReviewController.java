package com.example.appapi.store.review.controller;

import com.example.appapi.store.review.model.StoreReviewDto;
import com.example.appapi.store.review.service.StoreReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/review")
public class StoreReviewController {
    private final StoreReviewService storeReviewService;

    @PostMapping("/create")
    public ResponseEntity<StoreReviewDto.ReviewRes> create(
            @RequestPart StoreReviewDto.CreateReq dto,
            @RequestPart MultipartFile[] files) {
        StoreReviewDto.ReviewRes response = storeReviewService.create(dto, files);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<StoreReviewDto.ReviewRes>> list() {
        List<StoreReviewDto.ReviewRes> response = storeReviewService.getList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/read/{reviewIdx}")
    public ResponseEntity<StoreReviewDto.ReviewRes> read(@PathVariable Long reviewIdx) {
        StoreReviewDto.ReviewRes response = storeReviewService.get(reviewIdx);
        return ResponseEntity.ok(response);
    }
}
