package com.its.stationery.dto;

import com.its.stationery.entity.CartProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {
    private Long id;
    private String cartMemberId;
    private Long cartProductId;
    private String cartProductName;
    private String cartProductBrand;
    private LocalDateTime createdTime;
    private MultipartFile cartProductFile;
    private String cartProductFileName;
    private Long cartProductPrice;
    private int cartProductCounts;

    public static CartProductDTO toCartProductDTO(CartProductEntity cartProductEntity) {
        CartProductDTO cartProductDTO = new CartProductDTO();
        cartProductDTO.setId(cartProductEntity.getId());
        cartProductDTO.setCartMemberId(cartProductEntity.getCartMemberId());
        cartProductDTO.setCartProductCounts(cartProductEntity.getCartProductCounts());
        cartProductDTO.setCartProductBrand(cartProductEntity.getCartProductBrand());
        cartProductDTO.setCartProductName(cartProductEntity.getCartProductName());
        cartProductDTO.setCartProductId(cartProductEntity.getCartProductId());
        cartProductDTO.setCartProductPrice(cartProductEntity.getCartProductPrice());
        cartProductDTO.setCartProductFileName(cartProductEntity.getCartProductFileName());
        cartProductDTO.setCreatedTime(cartProductEntity.getCreatedTime());
        return cartProductDTO;
    }
}
