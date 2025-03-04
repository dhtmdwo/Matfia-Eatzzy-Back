package com.example.appapi.reservation;


import com.example.appapi.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT DISTINCT r FROM Reservation r " +
            "JOIN FETCH r.store s " +
            "LEFT JOIN FETCH s.images si " +
            "WHERE r.users.idx = :userId")
    List<Reservation> findReservationsByUserId(@Param("userId") Long userId);
}
