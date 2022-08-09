package com.its.stationery.repository;

import com.its.stationery.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByOrderMemberId(String orderMemberId);

    List<OrderEntity> findByOrderProductId(Long orderProductId);

    List<OrderEntity> findByOrderMemberIdAndOrderProductId(String orderMemberId, Long orderProductId);

    Page<OrderEntity> findByOrderMemberIdContainingIgnoreCase(String orderMemberId, Pageable pageable);
}
