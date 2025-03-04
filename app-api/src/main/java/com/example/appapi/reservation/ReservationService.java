package com.example.appapi.reservation;
import com.example.appapi.reservation.model.Reservation;
import com.example.appapi.reservation.model.ReservationDto;
import com.example.appapi.store.StoreRepository;
import com.example.appapi.store.images.model.StoreImagesRepository;
import com.example.appapi.store.model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<ReservationDto.StoreRezResponse> storeList(Long idx) {
        List<Reservation> reservations = reservationRepository.findReservationsByUserId(idx);

        List<ReservationDto.StoreRezResponse> responseList = new ArrayList<>();

        for (Reservation reservation : reservations) {
            Store store = reservation.getStore();
            ReservationDto.StoreRezResponse response = ReservationDto.StoreRezResponse.from(store, reservation);
            responseList.add(response);
        }

        return responseList;
    } // 마이페이지 클라이언트 예약한 식당 내역 보기

    public void deleteReservation(Long idx) {
        reservationRepository.findById(idx).ifPresentOrElse(
                reservationRepository::delete,
                () -> {
                    throw new IllegalArgumentException("해당 예약이 존재하지 않습니다");
                }
        );// idx 값으로 검색후 있으면 삭제 없으면 예외처리
    } // 마이페이지 클라이언트 예약 취소



}
