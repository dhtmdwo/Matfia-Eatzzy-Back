package com.example.appapi.orderProducts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
    List<OrderProducts> findAllByOrdersIdx(Long ordersIdx);
}
