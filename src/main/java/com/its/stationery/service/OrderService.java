package com.its.stationery.service;

import com.its.stationery.dto.OrderDTO;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.OrderEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.OrderRepository;
import com.its.stationery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    public Long save(OrderDTO orderDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(orderDTO.getOrderMemberId());
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(orderDTO.getOrderProductId());
        if(optionalMemberEntity.isPresent()){
            if(optionalProductEntity.isPresent()){
                Long saveId = orderRepository.save(OrderEntity.toSaveEntity(orderDTO,optionalMemberEntity.get(),optionalProductEntity.get())).getId();
                return saveId;
            }
        }
        return null;
    }

    public List<OrderDTO> findByOrderMemberId(String orderMemberId) {
        List<OrderEntity> orderEntityList = orderRepository.findByOrderMemberId(orderMemberId);
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(OrderEntity orderEntity: orderEntityList){
            orderDTOList.add(OrderDTO.toOrderDTO(orderEntity));
        }
        return orderDTOList;
    }

    public OrderDTO findById(Long id) {
        Optional<OrderEntity> optionalOrderEntity = orderRepository.findById(id);
        if(optionalOrderEntity.isPresent()){
            OrderEntity orderEntity = optionalOrderEntity.get();
            OrderDTO orderDTO = OrderDTO.toOrderDTO(orderEntity);
            return orderDTO;
        }
        return null;
    }
}
