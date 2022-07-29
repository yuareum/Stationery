package com.its.stationery.dto;

import com.its.stationery.entity.WishEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishDTO {
    private Long id;
    private String wishMemberId;

    public static WishDTO toWishDTO(WishEntity wishEntity) {
        WishDTO wishDTO = new WishDTO();
        wishDTO.setId(wishEntity.getId());
        wishDTO.setWishMemberId(wishEntity.getWishMemberId());
        return wishDTO;
    }
}
