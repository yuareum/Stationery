package com.its.stationery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String orderMemberId;
    private String orderProductName;
    private int orderCounts;
    private Long orderPrice;
    private String orderAddress;
    private String orderMobile;
    private LocalDateTime orderCreatedTime;
    private MultipartFile orderFile;
    private String orderFileName;
    private int adminProcess;
}
