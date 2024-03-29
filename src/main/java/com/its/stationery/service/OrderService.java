package com.its.stationery.service;

import com.its.stationery.common.PagingConst;
import com.its.stationery.config.WebConfig;
import com.its.stationery.dto.OrderDTO;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.OrderEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.OrderRepository;
import com.its.stationery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public Page<OrderDTO> findByOrderMemberId(String orderMemberId, Pageable pageable) {
        int page = pageable.getPageNumber();
        page  = (page == 1) ? 0 : (page -1);
        Page<OrderEntity> orderEntity = null;
        orderRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        orderEntity = orderRepository.findByOrderMemberIdContainingIgnoreCase(orderMemberId,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<OrderDTO> orderList = orderEntity.map(
                order -> new OrderDTO(order.getId(),
                        order.getOrderMemberId(),
                        order.getOrderFileName(),
                        order.getOrderProductName(),
                        order.getOrderProductId(),
                        order.getAdminProcess(),
                        order.getOrderCreatedTime(),
                        order.getOrderCounts(),
                        order.getOrderPrice()
                ));
        return orderList;
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

    public Page<OrderDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1)? 0: (page-1);
        Page<OrderEntity> orderEntities = orderRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<OrderDTO> orderList = orderEntities.map(
                order -> new OrderDTO(order.getId(),
                        order.getOrderProductId(),
                        order.getOrderFileName(),
                        order.getOrderMemberId(),
                        order.getOrderProductName(),
                        order.getOrderCounts(),
                        order.getOrderPrice(),
                        order.getOrderCreatedTime(),
                        order.getAdminProcess()
                ));
        return orderList;
    }


    public Long processUpdate(OrderDTO orderDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(orderDTO.getOrderMemberId());
        if(optionalMemberEntity.isPresent()){
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(orderDTO.getOrderProductId());
            if(optionalMemberEntity.isPresent()){
                OrderEntity orderEntity = OrderEntity.toUpdateEntity(orderDTO,optionalMemberEntity.get(),optionalProductEntity.get());
                Long id = orderRepository.save(orderEntity).getId();
                return id;
            }
        }
        return null;
    }

    public List<OrderDTO> check(OrderDTO orderDTO) {
        List<OrderEntity> optionalOrderEntity = orderRepository.findByOrderMemberIdAndOrderProductId(orderDTO.getOrderMemberId(), orderDTO.getOrderProductId());
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for(OrderEntity order : optionalOrderEntity){
            if(order.getAdminProcess() == 1){
                orderDTOList.add(OrderDTO.toOrderDTO(order));
            }
        }
        return orderDTOList;
    }


}
