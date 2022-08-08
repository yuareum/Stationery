package com.its.stationery.repository;

import com.its.stationery.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByReviewProductId(Long reviewProductId);


    Optional<ReviewEntity> findByReviewWriterAndReviewProductId(String reviewWriter, Long reviewProductId);

    List<ReviewEntity> findByReviewWriter(String reviewWriter);


}
