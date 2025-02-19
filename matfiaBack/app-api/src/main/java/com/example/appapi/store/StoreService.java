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

    public void create(StoreDto.CreateStoreRequestDto dto) {
        Store store = storeRepository.save(dto.toEntity());

        dto.getClosedDayList().forEach(closedDayRequestDto -> {
            storeClosedDayRepository.save(closedDayRequestDto.toEntity(store));
        });
    }

}
