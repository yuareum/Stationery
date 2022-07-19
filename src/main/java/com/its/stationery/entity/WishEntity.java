package com.its.stationery.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "wish_table")
public class WishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 50)
    private String wishFileName;

    @Column(length = 50, nullable = false)
    private String wishProductName;

    @Column(length = 30, nullable = false)
    private String wishProductBrand;

    @Column(length = 30, nullable = false)
    private String wishProductCreatedTime;

    @Column(nullable = false)
    private LocalDateTime wishCreatedTime;

    @Column(nullable = false)
    private Long wishPrice;

    @Column(length = 20, nullable = false)
    private String wishMemberId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

}
