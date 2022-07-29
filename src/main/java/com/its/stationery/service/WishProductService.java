package com.its.stationery.service;

import com.its.stationery.dto.WishProductDTO;
import com.its.stationery.entity.WishProductEntity;
import com.its.stationery.repository.WishProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishProductService {
    private final WishProductRepository wishProductRepository;

    public WishProductDTO findByWishProductNameAndWishMemberId(String wishProductName, String wishMemberId) {
        WishProductEntity wishProductEntity = wishProductRepository.findByWishProductNameAndWishMemberId(wishProductName, wishMemberId);
        if(wishProductEntity != null){
            WishProductDTO wishProductDTO = WishProductDTO.toWishProductDTO(wishProductEntity);
            return wishProductDTO;
        }
        return null;
    }
}
