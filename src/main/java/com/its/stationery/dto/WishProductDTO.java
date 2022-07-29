package com.its.stationery.dto;

import com.its.stationery.entity.WishProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishProductDTO {
    private Long id;
    private String wishMemberId;
    private String wishProductName;
    private String wishProductBrand;
    private Long wishPrice;
    private String wishProductCreatedTime;
    private MultipartFile wishFile;
    private String wishFileName;

    public static WishProductDTO toWishProductDTO(WishProductEntity wishProductEntity) {
        WishProductDTO wishProductDTO = new WishProductDTO();
        wishProductDTO.setId(wishProductEntity.getId());
        wishProductDTO.setWishProductName(wishProductEntity.getWishProductName());
        wishProductDTO.setWishProductBrand(wishProductEntity.getWishProductBrand());
        wishProductDTO.setWishMemberId(wishProductEntity.getWishMemberId());
        wishProductDTO.setWishPrice(wishProductEntity.getWishPrice());
        wishProductDTO.setWishProductCreatedTime(wishProductEntity.getWishProductCreatedTime());
        wishProductDTO.setWishFileName(wishProductEntity.getWishFileName());
        return wishProductDTO;
    }
}
