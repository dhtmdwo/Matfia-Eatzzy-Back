package com.example.appapi.store;

import com.example.appapi.store.model.StoreDto;
import com.example.appapi.users.model.Users;
import com.example.common.BaseResponse;
import com.example.common.BaseResponseStatus;
import com.example.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/store")
public class StoreController {
    private final StoreService storeService;

    // 식당 생성
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<StoreDto.StoreResponseDto>> createStore(
            @AuthenticationPrincipal Users user,
            @RequestBody StoreDto.CreateStoreRequestDto dto) {
        StoreDto.StoreResponseDto createStoreRes = storeService.create(dto, user);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, createStoreRes));
    }

    // 식당 목록 조회
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<StoreDto.StorePageResponseDto>> getStoreList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        StoreDto.StorePageResponseDto response = storeService.list(page, size);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, response));
    }

    // 식당 상세 조회
    @GetMapping("/{storeIdx}")
    public ResponseEntity<BaseResponse<StoreDto.StoreResponseDto>> getStore(@PathVariable Long storeIdx) {
        StoreDto.StoreResponseDto response = storeService.getStore(storeIdx);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, response));
    }

}


