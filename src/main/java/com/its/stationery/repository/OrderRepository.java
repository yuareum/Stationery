package com.its.stationery.repository;

import com.its.stationery.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByOrderMemberId(String orderMemberId);

    OrderEntity findByOrderProductName(String productName);
}
