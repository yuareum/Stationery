package com.its.stationery.service;

import com.its.stationery.dto.CartProductDTO;
import com.its.stationery.entity.CartEntity;
import com.its.stationery.entity.CartProductEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.repository.CartProductRepository;
import com.its.stationery.repository.CartRepository;
import com.its.stationery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartProductService {
    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public List<CartProductDTO> findByCartMemberId(String cartMemberId) {
        List<CartProductEntity> cartProductEntityList = cartProductRepository.findByCartMemberId(cartMemberId);
        List<CartProductDTO> cartProductDTOList  = new ArrayList<>();
        for(CartProductEntity cartProduct : cartProductEntityList){
            cartProductDTOList.add(CartProductDTO.toCartProductDTO(cartProduct));
        }
        return cartProductDTOList;
    }

    public int save(CartProductDTO cartProductDTO) {
        Optional<CartEntity> optionalCartEntity = cartRepository.findByCartMemberId(cartProductDTO.getCartMemberId());
        if(optionalCartEntity.isPresent()){
            Optional<ProductEntity> productEntity = productRepository.findById(cartProductDTO.getCartProductId());
            if(productEntity.isPresent()){
                CartProductEntity cartProductEntity = CartProductEntity.toCartProductEntity(cartProductDTO,optionalCartEntity.get(),productEntity.get());
                cartProductRepository.save(cartProductEntity);
                return 1;
            }
        }
        return 0;
    }

    public int check(CartProductDTO cartProductDTO) {
        CartProductEntity cartProductEntity = cartProductRepository.findByCartMemberIdAndCartProductName(cartProductDTO.getCartMemberId(),cartProductDTO.getCartProductName());
        if(cartProductEntity == null){
            return 1;
        }
        return 0;
    }

    public void delete(Long id) {
        cartProductRepository.deleteById(id);
    }
}
