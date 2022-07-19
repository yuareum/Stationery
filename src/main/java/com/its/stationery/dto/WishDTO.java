package com.its.stationery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishDTO {
    private String wishProductName;
    private String wishProductBrand;
    private Long wishPrice;
    private String wishMemberId;
    private LocalDateTime wishCreatedTime;
    private MultipartFile wishFile;
    private String wishFileName;
}
