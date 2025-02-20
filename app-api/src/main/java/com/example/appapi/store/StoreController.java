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
    public ResponseEntity<BaseResponse<StoreDto.StoreResponseDto>> createStore(@RequestBody StoreDto.CreateStoreRequestDto dto) {
        StoreDto.StoreResponseDto createStoreRes = storeService.create(dto);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, createStoreRes));
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<StoreDto.StorePageResponseDto>> getStoreList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        StoreDto.StorePageResponseDto response = storeService.list(page, size);

        return ResponseEntity.ok(new BaseResponse(BaseResponseStatus.SUCCESS, response));
    }

}
