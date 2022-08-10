package com.its.stationery.service;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.CartProductDTO;
import com.its.stationery.dto.OrderDTO;
import com.its.stationery.entity.CartEntity;
import com.its.stationery.entity.CartProductEntity;
import com.its.stationery.entity.OrderEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.repository.CartProductRepository;
import com.its.stationery.repository.CartRepository;
import com.its.stationery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<CartProductDTO> findByCartMemberId(String cartMemberId, Pageable pageable) {
        int page = pageable.getPageNumber();
        page  = (page == 1) ? 0 : (page -1);
        Page<CartProductEntity> cartProductEntity = null;
        cartProductRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        cartProductEntity = cartProductRepository.findByCartMemberIdContainingIgnoreCase(cartMemberId,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<CartProductDTO> cartProductList = cartProductEntity.map(
                cartProduct -> new CartProductDTO(cartProduct.getId(),
                        cartProduct.getCartProductId(),
                        cartProduct.getCartProductName(),
                        cartProduct.getCartProductFileName(),
                        cartProduct.getCartProductBrand(),
                        cartProduct.getCartProductCounts(),
                        cartProduct.getCartProductPrice(),
                        cartProduct.getCreatedTime()));
        return cartProductList;
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
