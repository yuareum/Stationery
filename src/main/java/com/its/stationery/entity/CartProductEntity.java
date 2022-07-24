package com.its.stationery.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "cart_product_table")
public class CartProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20)
    private String cartMemberId;

    @Column(length = 50)
    private String cartProductFileName;

    @Column(length = 50, nullable = false)
    private String cartProductName;

    @Column(length = 30, nullable = false)
    private String cartProductBrand;

    @Column(length = 30, nullable = false)
    private String cartProductCreatedTime;

    @Column(nullable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private Long cartProductPrice;

    @Column(nullable = false)
    private int cartProductCounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

}
