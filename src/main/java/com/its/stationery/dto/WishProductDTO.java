package com.its.stationery.dto;

import com.its.stationery.entity.WishProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishProductDTO {
    private Long id;
    private String wishMemberId;
    private Long wishProductId;
    private String wishProductName;
    private String wishProductBrand;
    private Long wishPrice;

    private MultipartFile wishFile;
    private String wishFileName;
    private LocalDateTime createdTime;

    public static WishProductDTO toWishProductDTO(WishProductEntity wishProductEntity) {
        WishProductDTO wishProductDTO = new WishProductDTO();
        wishProductDTO.setId(wishProductEntity.getId());
        wishProductDTO.setWishProductId(wishProductEntity.getWishProductId());
        wishProductDTO.setWishProductName(wishProductEntity.getWishProductName());
        wishProductDTO.setWishProductBrand(wishProductEntity.getWishProductBrand());
        wishProductDTO.setWishMemberId(wishProductEntity.getWishMemberId());
        wishProductDTO.setWishPrice(wishProductEntity.getWishPrice());
        wishProductDTO.setWishFileName(wishProductEntity.getWishFileName());
        wishProductDTO.setCreatedTime(wishProductEntity.getCreatedTime());
        return wishProductDTO;
    }
}
