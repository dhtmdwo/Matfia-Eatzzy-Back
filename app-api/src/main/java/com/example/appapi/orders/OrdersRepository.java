package com.example.appapi.orders;

import com.example.appapi.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByIdx(Long idx);
}
