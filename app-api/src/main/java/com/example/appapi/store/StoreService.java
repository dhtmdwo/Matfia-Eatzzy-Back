package com.example.appapi.store;

import com.example.appapi.store.model.Store;
import com.example.appapi.store.model.StoreClosedDay;
import com.example.appapi.store.model.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreClosedDayRepository storeClosedDayRepository;

    @Transactional
    public StoreDto.StoreResponseDto create(StoreDto.CreateStoreRequestDto dto) {
        Store store = storeRepository.save(dto.toEntity());

        List<StoreClosedDay> closedDays = dto.getClosedDayList().stream()
                .map(closedDayRequestDto -> closedDayRequestDto.toEntity(store))
                .toList();

        storeClosedDayRepository.saveAll(closedDays);

        List<StoreDto.ClosedDayResponseDto> closedDayResponseList = closedDays.stream()
                .map(StoreDto.ClosedDayResponseDto::from)
                .toList();

        return StoreDto.StoreResponseDto.from(store, closedDayResponseList);
    }
}
