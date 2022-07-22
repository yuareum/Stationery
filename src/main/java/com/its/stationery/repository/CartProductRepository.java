package com.its.stationery.repository;

import com.its.stationery.entity.CartProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {

    List<CartProductEntity> list(String cartMemberId);
}
