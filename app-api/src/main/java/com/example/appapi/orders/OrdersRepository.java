package com.example.appapi.orders;

import com.example.appapi.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByIdx(Long idx);

    @Query("SELECT DISTINCT o FROM Orders o " +
            "JOIN FETCH o.orderProducts op " +
            "JOIN FETCH op.products p")
    List<Orders> findAllWithOrderProductsAndProducts();

    @Modifying
    @Query("UPDATE Orders o SET o.price = :totalPrice WHERE o.idx = :orderIdx")
    int updateOrderPrice(@Param("orderIdx") Long orderId, @Param("totalPrice") int totalPrice);

    @Modifying
    @Query("UPDATE Orders o SET o.message = :message, o.status = 'Paying' WHERE o.idx = :orderIdx")
    int updateOrderMessageAndStatus(@Param("orderIdx") Long orderId,
                                    @Param("message") String message);


    @Query("SELECT DISTINCT o FROM Orders o " +
            "JOIN FETCH o.orderProducts op " +
            "JOIN FETCH op.products pr " +
            "JOIN FETCH o.payment p "+
            "WHERE o.user.idx = :userId")
    List<Orders> findMyAllOrders(@Param("userId") Long userId); // 마이페이지 클라이언트 주문 목록 보기

}
