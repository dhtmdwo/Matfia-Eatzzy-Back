package com.example.appapi.orderProducts;

import com.example.appapi.orderProducts.model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
    //List<OrderProducts> findAllByOrdersIdx(Long ordersIdx);

    @Query("SELECT op FROM OrderProducts op JOIN FETCH op.products WHERE op.orders.idx = :ordersIdx")
    List<OrderProducts> findAllByOrdersIdx(@Param("ordersIdx") Long ordersIdx);
}
