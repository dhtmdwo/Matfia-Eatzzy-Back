package com.example.resv.resv;

import com.example.appapi.reservation.model.Reservation;
import com.example.appapi.reservation.model.ReservationDto;
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

import java.util.ArrayList;
import java.util.List;
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

    public List<ResvDto.StoreRezResponse> storeList(Long idx) {
        List<Resv> reservations = resvRepository.findReservationsByUserId(idx);

        List<ResvDto.StoreRezResponse> responseList = new ArrayList<>();

        for (Resv reservation : reservations) {
            Store store = reservation.getStore();
            ResvDto.StoreRezResponse response = ResvDto.StoreRezResponse.from(store, reservation);
            responseList.add(response);
        }

        return responseList;
    } // 마이페이지 클라이언트 예약한 식당 내역 보기

    public void deleteReservation(Long idx) {
        resvRepository.findById(idx).ifPresentOrElse(
                resvRepository::delete,
                () -> {
                    throw new IllegalArgumentException("해당 예약이 존재하지 않습니다");
                }
        );// idx 값으로 검색후 있으면 삭제 없으면 예외처리
    } // 마이페이지 클라이언트 예약 취소


}
