package com.its.stationery.service;

import com.its.stationery.dto.WishProductDTO;
import com.its.stationery.entity.CartProductEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.entity.WishEntity;
import com.its.stationery.entity.WishProductEntity;
import com.its.stationery.repository.ProductRepository;
import com.its.stationery.repository.WishProductRepository;

import com.its.stationery.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishProductService {
    private final WishRepository wishRepository;
    private final ProductRepository productRepository;
    private final WishProductRepository wishProductRepository;

    public WishProductDTO findByWishMemberIdAndWishProductName(String wishMemberId, String wishProductName) {
        WishProductEntity wishProductEntity = wishProductRepository.findByWishMemberIdAndWishProductName(wishMemberId, wishProductName);
        if(wishProductEntity != null){
            WishProductDTO wishProductDTO = WishProductDTO.toWishProductDTO(wishProductEntity);
            return wishProductDTO;
        }
        return null;
    }

    public int save(WishProductDTO wishProductDTO) {
        Optional<WishEntity> optionalWishEntity = wishRepository.findByWishMemberId(wishProductDTO.getWishMemberId());
        if(optionalWishEntity.isPresent()){
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(wishProductDTO.getWishProductId());
            if(optionalProductEntity.isPresent()){
                WishProductEntity wishProductEntity = WishProductEntity.toWishProductEntity(wishProductDTO,optionalWishEntity.get(),optionalProductEntity.get());
                wishProductRepository.save(wishProductEntity);
                return 1;
            }
        }
        return 0;
    }

    public int check(WishProductDTO wishProductDTO) {
        WishProductEntity wishProductEntity = wishProductRepository.findByWishMemberIdAndWishProductName(wishProductDTO.getWishMemberId(),wishProductDTO.getWishProductName());
        if(wishProductEntity == null){
            return 1;
        }
        return 0;
    }

    public void delete(Long id) {
        wishProductRepository.deleteById(id);
    }
}
