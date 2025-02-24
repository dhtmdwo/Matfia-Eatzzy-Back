package com.example.appapi.delivery.model;

import com.example.appapi.orders.model.Orders;
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
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String delivery_status; // 배송 상태
    private String courier_company; // 택배 회사
    private int tracking_number;    // 운송장 번호

    @OneToOne
    @JoinColumn(name = "order_idx") // 주문 번호
    private Orders orders;
}
