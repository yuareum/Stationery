package com.its.stationery.entity;

import lombok.Getter;
import lombok.Setter;

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

    @Column(length = 50)
    private String wishFileName;

    @Column(length = 50, nullable = false)
    private String wishProductName;

    @Column(length = 30, nullable = false)
    private String wishProductBrand;

    @Column(length = 30, nullable = false)
    private String wishProductCreatedTime;

    @Column(nullable = false)
    private Long wishPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wish_id")
    private WishEntity wishEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
