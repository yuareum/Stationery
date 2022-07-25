package com.its.stationery.service;

import com.its.stationery.dto.CartProductDTO;
import com.its.stationery.entity.CartProductEntity;
import com.its.stationery.repository.CartProductRepository;
import com.its.stationery.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartProductService {
    private final CartProductRepository cartProductRepository;

    public List<CartProductDTO> findByCartMemberId(String cartMemberId) {
        List<CartProductEntity> cartProductEntityList = cartProductRepository.findByCartMemberId(cartMemberId);
        List<CartProductDTO> cartProductDTOList  = new ArrayList<>();
        for(CartProductEntity cartProduct : cartProductEntityList){
            cartProductDTOList.add(CartProductDTO.toCartProductDTO(cartProduct));
        }
        return cartProductDTOList;
    }

}
