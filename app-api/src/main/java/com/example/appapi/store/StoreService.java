package com.example.appapi.store;

import com.example.appapi.category.CategoryRepository;
import com.example.appapi.category.model.Category;
import com.example.appapi.store.model.AllowedStatus;
import com.example.appapi.store.model.Store;
import com.example.appapi.store.model.StoreClosedDay;
import com.example.appapi.store.model.StoreDto;
import com.example.appapi.users.model.Users;
import com.example.common.BaseResponseStatus;
import com.example.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreClosedDayRepository storeClosedDayRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public StoreDto.StoreResponseDto create(StoreDto.CreateStoreRequestDto dto, Users user) {
        Category category = categoryRepository.findById(dto.getCategoryIdx()).orElseThrow(() ->  new BaseException(BaseResponseStatus.STORE_REGIST_FAILED));

        Store store = storeRepository.save(dto.toEntity(user, category));

        List<StoreClosedDay> closedDays = dto.getClosedDayList().stream()
                .map(closedDayRequestDto -> closedDayRequestDto.toEntity(store))
                .toList();

        storeClosedDayRepository.saveAll(closedDays);

        List<StoreDto.ClosedDayResponseDto> closedDayResponseList = closedDays.stream()
                .map(StoreDto.ClosedDayResponseDto::from)
                .toList();

        return StoreDto.StoreResponseDto.fromWithClosedDays(store, closedDayResponseList);
    }

    public StoreDto.StorePageResponseDto list(int page, int size) {
        Page<Store> result = storeRepository.findByAllowed(AllowedStatus.YES, PageRequest.of(page, size));
        return StoreDto.StorePageResponseDto.from(result);
    }

    public StoreDto.StoreResponseDto getStore(Long storeIdx) {
        Store store = storeRepository.findByIdWithClosedDaysAndUserAndCategory(storeIdx).orElseThrow(
                () ->  new BaseException(BaseResponseStatus.STORE_NOT_FOUND)
        );

        return StoreDto.StoreResponseDto.from(store);
    }

}
