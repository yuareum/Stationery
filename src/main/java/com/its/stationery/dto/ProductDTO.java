package com.its.stationery.dto;

import com.its.stationery.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private int productCounts;
    private String productName;
    private String productAdmin;
    private String productBrand;
    private Long productPrice;
    private String productInformation;
    private String productCreatedTime;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private MultipartFile productFile;
    private String productFileName;
    private int productHits;

    public ProductDTO(Long id, String productName, String productBrand, Long productPrice, String productFileName) {
        this.id = id;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
        this.productFileName = productFileName;
    }

    public static ProductDTO toProductDTO(ProductEntity productEntity) {
    }
}
