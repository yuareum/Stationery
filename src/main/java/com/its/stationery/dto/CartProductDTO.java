package com.its.stationery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {
    private String cartProductName;
    private String cartProductBrand;
    private String cartProductCreatedTime;
    private LocalDateTime createdTime;
    private MultipartFile cartProductFile;
    private String cartProductFileName;
    private Long cartProductPrice;
    private int cartProductCounts;
}
