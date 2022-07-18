package com.its.stationery.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String id;

    @Column(length = 20, nullable = false)
    private String paymentMemberId;

    @Column(nullable = false)
    private Long paymentPrice;

    @Column(length = 50, nullable = false)
    private String paymentAddress;

    @Column(length = 20, nullable = false)
    private String paymentMemberMobile;

    @Column(nullable = false)
    private LocalDateTime paymentCreatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
}
