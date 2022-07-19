package com.its.stationery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private String paymentMemberId;
    private Long paymentPrice;
    private String paymentAddress;
    private String paymentMemberMobile;
    private LocalDateTime paymentCreatedTime;

}
