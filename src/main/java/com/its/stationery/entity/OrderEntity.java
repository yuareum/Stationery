package com.its.stationery.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "order_table")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private int orderCounts;

    @Column(length = 20, nullable = false)
    private String orderMemberId;

    @Column(length = 50, nullable = false)
    private String orderProductName;

    @Column(nullable = false)
    private Long orderPrice;

    @Column(length = 50, nullable = false)
    private String orderAddress;

    @Column(length = 20, nullable = false)
    private String orderMobile;

    @Column(nullable = false)
    private LocalDateTime orderCreatedTime;

    @Column(length = 50)
    private String orderFileName;

    @ColumnDefault("0")
    @Column(nullable = false)
    private int adminProcess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;




}
