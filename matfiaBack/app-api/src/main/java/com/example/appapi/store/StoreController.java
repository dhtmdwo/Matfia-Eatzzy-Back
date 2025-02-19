package com.example.appapi.store;

import com.example.appapi.store.model.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody StoreDto.CreateStoreRequestDto dto) {
        storeService.create(dto);
        return ResponseEntity.ok("success");
    }
}
