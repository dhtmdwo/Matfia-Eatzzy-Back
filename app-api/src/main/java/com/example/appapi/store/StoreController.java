package com.example.appapi.store;

import com.example.appapi.store.model.StoreDto;
import com.example.common.BaseResponse;
import com.example.common.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<StoreDto.StoreResponseDto>> create(@RequestBody StoreDto.CreateStoreRequestDto dto) {
        StoreDto.StoreResponseDto createStoreRes = storeService.create(dto);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, createStoreRes));
    }
//
//    @GetMapping("/list")
//    public ResponseEntity<CourseDto.CoursePageResponse> list(int page, int size) {
//        CourseDto.CoursePageResponse response = courseService.list(page, size);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/{courseIdx}")
//    public ResponseEntity<CourseDto.CourseResponse> read(@PathVariable Long courseIdx) {
//        CourseDto.CourseResponse response = courseService.read
//                (courseIdx);
//
//        return ResponseEntity.ok(response);
//    }
}
