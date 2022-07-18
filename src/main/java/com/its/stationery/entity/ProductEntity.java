package com.its.stationery.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "product")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private int productCounts;

    @Column(length = 50, nullable = false)
    private String productName;

    @Column(length = 20, nullable = false)
    private String productAdmin;

    @Column(length = 30, nullable = false)
    private String productBrand;

    @Column(nullable = false)
    private Long productPrice;

    @Column(nullable = false)
    private String productCreatedTime;

    @Column(length = 50)
    private String productFileName;

    @Column
    private int productHits;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ShoppingCartEntity> shoppingCartEntityList = new ArrayList<>();

    @ManyToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE)
    private List<WishEntity> wishEntityList = new ArrayList<>();




}
