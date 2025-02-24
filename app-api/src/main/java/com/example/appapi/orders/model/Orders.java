package com.example.appapi.orders.model;

import com.example.appapi.payment.model.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private int quantity;
    private int price;
    private String message;
    private String status;

    @OneToOne
    @JoinColumn(name = "Payment_idx")
    private Payment payment;

}