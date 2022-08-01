package com.its.stationery.repository;

import com.its.stationery.entity.WishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishRepository extends JpaRepository<WishEntity, Long> {

    Optional<WishEntity> findByWishMemberId(String wishMemberId);
}
