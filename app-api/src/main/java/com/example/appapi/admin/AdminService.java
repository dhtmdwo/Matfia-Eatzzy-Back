package com.example.appapi.admin;

import com.example.appapi.store.StoreRepository;
import com.example.appapi.store.model.AllowedStatus;
import com.example.appapi.store.model.Store;
import com.example.appapi.store.model.StoreDto;
import com.example.common.BaseResponseStatus;
import com.example.common.exception.BaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final StoreRepository storeRepository;

    public StoreDto.StorePageResponseDto storeListAll(int page, int size) {
        Page<Store> result = storeRepository.findAll(PageRequest.of(page, size));
        return StoreDto.StorePageResponseDto.from(result);
    }

    public StoreDto.StoreResponseDto getStore(Long storeIdx) {
        Store store = storeRepository.findById(storeIdx).orElseThrow(
                () ->  new BaseException(BaseResponseStatus.STORE_NOT_FOUND)
        );

        return StoreDto.StoreResponseDto.from(store);
    }

    @Transactional
    public StoreDto.StoreResponseDto updateStoreAllowed(Long storeIdx, AllowedStatus allowed) {
        // Store 조회
        Store store = storeRepository.findById(storeIdx)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + storeIdx));

        // AllowedStatus 수정
        store.setAllowed(allowed);

        // 변경된 Store 저장 (JPA는 @Transactional 내에서 변경 사항을 자동으로 반영)
        storeRepository.save(store);

        // 수정된 Store를 DTO로 변환하여 반환
        return StoreDto.StoreResponseDto.from(store);
    }
}
