package com.example.resv.resv;

import com.example.appapi.store.StoreRepository;
import com.example.appapi.store.model.Store;
import com.example.appapi.users.model.Users;
import com.example.common.BaseResponseStatus;
import com.example.common.exception.BaseException;
import com.example.resv.resv.model.Resv;
import com.example.resv.resv.model.ResvDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ResvService {
    private final ResvRepository resvRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ResvDto.ResvResponse create(ResvDto.CreateResvRequest dto, Users user) {

        Store store = storeRepository.findById(dto.getStoreIdx())
                .orElseThrow(() ->  new BaseException(BaseResponseStatus.STORE_NOT_FOUND));

        Resv resv = resvRepository.save(dto.toEntity(user, store));

        return ResvDto.ResvResponse.from(resv);
    }

}
