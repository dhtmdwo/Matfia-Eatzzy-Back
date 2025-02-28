package com.example.appapi.reservation.model;

import com.example.appapi.store.model.Store;
import com.example.appapi.users.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String date;
    private String time;
    private String name;        // 예약자 이름
    private int headCount;      // 인원 수
    private String request;     // 요청사항


    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;        // store 연관

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;        // user 연관

}
