package com.its.stationery.repository;

import com.its.stationery.entity.WishProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishProductRepository extends JpaRepository<WishProductEntity, Long> {

    WishProductEntity findByWishProductNameAndWishMemberId(String wishProductName, String wishMemberId);
}
