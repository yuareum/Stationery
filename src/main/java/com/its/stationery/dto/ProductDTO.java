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
    private Long categoryId;
    private Long productPrice;
    private String productInformation;
    private String productCreatedTime;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private int productHits;
    private MultipartFile productFile;
    private String productFileName;

    public ProductDTO(Long id, String productName, Long productPrice, String productFileName, Long categoryId) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productFileName = productFileName;
        this.categoryId = categoryId;
    }

    public static ProductDTO toProductDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setProductCounts(productEntity.getProductCounts());
        productDTO.setProductAdmin(productEntity.getProductAdmin());
        productDTO.setProductBrand(productEntity.getProductBrand());
        productDTO.setProductHits(productEntity.getProductHits());
        productDTO.setProductInformation(productEntity.getProductInformation());
        productDTO.setProductName(productEntity.getProductName());
        productDTO.setProductPrice(productEntity.getProductPrice());
        productDTO.setProductCreatedTime(productEntity.getProductCreatedTime());
        productDTO.setProductFileName(productEntity.getProductFileName());
        productDTO.setCreatedTime(productEntity.getCreatedTime());
        productDTO.setUpdatedTime(productEntity.getUpdatedTime());
        return productDTO;
    }
}
