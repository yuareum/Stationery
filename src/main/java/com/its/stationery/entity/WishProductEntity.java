package com.its.stationery.entity;

import com.its.stationery.dto.WishProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "wish_product_table")
public class WishProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20)
    private String wishMemberId;

    @Column
    private Long wishProductId;

    @Column(length = 50)
    private String wishFileName;

    @Column(length = 50, nullable = false)
    private String wishProductName;

    @Column(length = 30, nullable = false)
    private String wishProductBrand;

    @Column(nullable = false)
    private Long wishPrice;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wish_id")
    private WishEntity wishEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    public static WishProductEntity toWishProductEntity(WishProductDTO wishProductDTO, WishEntity wishEntity, ProductEntity productEntity) {
        WishProductEntity wishProductEntity = new WishProductEntity();
        wishProductEntity.setWishProductId(wishProductDTO.getWishProductId());
        wishProductEntity.setWishProductBrand(wishProductDTO.getWishProductBrand());
        wishProductEntity.setWishProductName(wishProductDTO.getWishProductName());
        wishProductEntity.setWishPrice(wishProductDTO.getWishPrice());
        wishProductEntity.setWishMemberId(wishProductDTO.getWishMemberId());
        wishProductEntity.setWishFileName(wishProductDTO.getWishFileName());
        wishProductEntity.setWishEntity(wishEntity);
        wishProductEntity.setProductEntity(productEntity);
        return wishProductEntity;
    }
}
