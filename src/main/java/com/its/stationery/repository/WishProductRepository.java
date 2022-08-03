package com.its.stationery.repository;

import com.its.stationery.entity.WishProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishProductRepository extends JpaRepository<WishProductEntity, Long> {


    WishProductEntity findByWishMemberIdAndWishProductName(String wishMemberId, String wishProductName);

    List<WishProductEntity> findByWishMemberId(String wishMemberId);
}
