package com.its.stationery.repository;

import com.its.stationery.entity.OrderEntity;
import com.its.stationery.entity.ProductEntity;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Modifying
    @Query(value = "update ProductEntity p set p.productHits= p.productHits+1 where p.id= :id")
    void productHits(@Param("id") Long id);

    List<ProductEntity> findByCategoryId(Long categoryId);

    List<ProductEntity> findByProductNameContainingOrProductBrandContaining(String q, String q1);
    void productCounts(int i);

}
