package com.example.appapi.orders;

import com.example.appapi.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByIdx(Long idx);

    @Query("SELECT DISTINCT o FROM Orders o " +
            "JOIN FETCH o.orderProducts op " +
            "JOIN FETCH op.products p")
    List<Orders> findAllWithOrderProductsAndProducts();

    @Query("SELECT DISTINCT o FROM Orders o " +
            "JOIN FETCH o.orderProducts op " +
            "JOIN FETCH op.products p " +
            "WHERE o.idx = :ordersIdx")
    Optional<Orders> findByIdWithOrderProductsAndProducts(@Param("ordersIdx") Long ordersIdx);


    @Modifying
    @Query("UPDATE Orders o SET o.price = :totalPrice WHERE o.idx = :orderIdx")
    int updateOrderPrice(@Param("orderIdx") Long orderId, @Param("totalPrice") int totalPrice);

    @Modifying
    @Query("UPDATE Orders o SET o.message = :message, o.status = 'Paying' WHERE o.idx = :orderIdx")
    int updateOrderMessageAndStatus(@Param("orderIdx") Long orderId,
                                    @Param("message") String message);
}
