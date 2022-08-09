package com.its.stationery.dto;

import com.its.stationery.entity.OrderEntity;
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
    private Long orderProductId;
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

    public OrderDTO(Long id, Long orderProductId, String orderFileName, String orderMemberId, String orderProductName, int orderCounts, Long orderPrice, LocalDateTime orderCreatedTime, int adminProcess) {
        this.id = id;
        this.orderProductId = orderProductId;
        this.orderFileName = orderFileName;
        this.orderProductName = orderProductName;
        this.orderPrice = orderPrice;
        this.orderCounts = orderCounts;
        this.orderCreatedTime = orderCreatedTime;
        this.adminProcess = adminProcess;
        this.orderMemberId = orderMemberId;
    }
    public OrderDTO(Long id, String orderMemberId, String orderFileName, String orderProductName, Long orderProductId, int adminProcess, LocalDateTime orderCreatedTime, int orderCounts, Long orderPrice) {
        this.id = id;
        this.orderMemberId = orderMemberId;
        this.orderFileName = orderFileName;
        this.orderPrice = orderPrice;
        this.orderCounts = orderCounts;
        this.orderProductName = orderProductName;
        this.orderCreatedTime = orderCreatedTime;
        this.orderProductId = orderProductId;
        this.adminProcess = adminProcess;
    }

    public static OrderDTO toOrderDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(orderEntity.getId());
        orderDTO.setOrderCounts(orderEntity.getOrderCounts());
        orderDTO.setOrderAddress(orderEntity.getOrderAddress());
        orderDTO.setOrderMobile(orderEntity.getOrderMobile());
        orderDTO.setOrderPrice(orderEntity.getOrderPrice());
        orderDTO.setOrderMemberId(orderEntity.getOrderMemberId());
        orderDTO.setOrderProductId(orderEntity.getOrderProductId());
        orderDTO.setOrderCreatedTime(orderEntity.getOrderCreatedTime());
        orderDTO.setOrderFileName(orderEntity.getOrderFileName());
        orderDTO.setOrderProductName(orderEntity.getOrderProductName());
        orderDTO.setAdminProcess(orderEntity.getAdminProcess());
        return orderDTO;
    }
}
