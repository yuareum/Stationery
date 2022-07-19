package com.its.stationery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDTO {
    private Long id;
    private String shoppingCartProductName;
    private String shoppingCartProductBrand;
    private LocalDateTime shoppingCartProductCreatedTime;
    private MultipartFile shoppingCartFile;
    private String shoppingCartFileName;
    private Long shoppingCartPrice;
    private String shoppingCartMemberId;

}
