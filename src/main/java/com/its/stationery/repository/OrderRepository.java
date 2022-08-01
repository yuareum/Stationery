package com.its.stationery.repository;

import com.its.stationery.dto.OrderDTO;
import com.its.stationery.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByOrderMemberId(String orderMemberId);

    Optional<OrderEntity> findByOrderProductId(Long orderProductId);

    Optional<OrderEntity> findByOrderMemberIdAndOrderProductId(String orderMemberId, Long orderProductId);
}
