package com.its.stationery.entity;

import com.its.stationery.dto.PaymentDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "payment_table")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20, nullable = false)
    private String paymentMemberId;

    @Column(nullable = false)
    private Long paymentPrice;

    @Column(length = 50, nullable = false)
    private String paymentAddress;

    @Column(length = 20, nullable = false)
    private String paymentMemberMobile;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime paymentCreatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;


    public static PaymentEntity toSaveEntity(PaymentDTO paymentDTO, MemberEntity memberEntity) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPaymentAddress(paymentDTO.getPaymentAddress());
        paymentEntity.setPaymentMemberId(paymentDTO.getPaymentMemberId());
        paymentEntity.setPaymentPrice(paymentDTO.getPaymentPrice());
        paymentEntity.setPaymentMemberMobile(paymentDTO.getPaymentMemberMobile());
        paymentEntity.setMemberEntity(memberEntity);
        return paymentEntity;
    }
}
