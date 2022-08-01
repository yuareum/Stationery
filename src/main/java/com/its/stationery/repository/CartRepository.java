package com.its.stationery.repository;

import com.its.stationery.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByCartMemberId(String cartMemberId);
}
