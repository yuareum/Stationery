package com.its.stationery.dto;

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
}
