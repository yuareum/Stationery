package com.its.stationery.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "shoppingCart")
public class ShoppingCartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20, nullable = false)
    private String shoppingCartMemberId;

    @Column(length = 50)
    private String shoppingCartFileName;

    @Column(length = 50, nullable = false)
    private String shoppingCartProductName;

    @Column(length = 30, nullable = false)
    private String shoppingCartProductBrand;

    @Column(length = 30, nullable = false)
    private String shoppingCartProductCreatedTime;

    @Column(nullable = false)
    private String shoppingCartCreatedTime;

    @Column(nullable = false)
    private Long shoppingCartPrice;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

}
