package com.example.appapi.orders.model;

import com.example.appapi.payment.model.Payment;
import com.example.appapi.delivery.model.Delivery;
import com.example.appapi.users.model.Users;
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

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private Users user;

    @OneToOne(mappedBy = "orders")
    private Delivery delivery;
}