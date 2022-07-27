package com.its.stationery.entity;

import com.its.stationery.dto.OrderDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column
    private Long orderProductId;

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

    @CreationTimestamp
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


    public static OrderEntity toSaveEntity(OrderDTO orderDTO, MemberEntity memberEntity, ProductEntity productEntity) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderCounts(orderDTO.getOrderCounts());
        orderEntity.setOrderAddress(orderDTO.getOrderAddress());
        orderEntity.setOrderProductName(orderDTO.getOrderProductName());
        orderEntity.setOrderMobile(orderDTO.getOrderMobile());
        orderEntity.setOrderMemberId(orderDTO.getOrderMemberId());
        orderEntity.setOrderPrice(orderDTO.getOrderPrice());
        orderEntity.setOrderProductId(orderDTO.getOrderProductId());
        orderEntity.setOrderFileName(orderDTO.getOrderFileName());
        orderEntity.setMemberEntity(memberEntity);
        orderEntity.setProductEntity(productEntity);
        return orderEntity;
    }

    public static OrderEntity toUpdateEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setOrderCounts(orderDTO.getOrderCounts());
        orderEntity.setOrderAddress(orderDTO.getOrderAddress());
        orderEntity.setOrderProductName(orderDTO.getOrderProductName());
        orderEntity.setOrderMobile(orderDTO.getOrderMobile());
        orderEntity.setOrderMemberId(orderDTO.getOrderMemberId());
        orderEntity.setOrderPrice(orderDTO.getOrderPrice());
        orderEntity.setAdminProcess(1);
        orderEntity.setOrderProductId(orderDTO.getOrderProductId());
        orderEntity.setOrderFileName(orderDTO.getOrderFileName());
        return orderEntity;
    }
}
