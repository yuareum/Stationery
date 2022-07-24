package com.its.stationery.dto;

import com.its.stationery.entity.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private String cartMemberId;

    public static CartDTO toCartDTO(CartEntity cartEntity) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cartEntity.getId());
        cartDTO.setCartMemberId(cartEntity.getCartMemberId());
        return cartDTO;
    }
}
