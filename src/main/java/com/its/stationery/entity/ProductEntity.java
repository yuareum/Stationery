package com.its.stationery.entity;

import com.its.stationery.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "product_table")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private int productCounts;

    @Column(length = 50, nullable = false)
    private String productName;

    @Column(length = 20, nullable = false)
    private String productAdmin;

    @Column(length = 30, nullable = false)
    private String productBrand;

    @Column(nullable = false)
    private Long productPrice;

    @Column(nullable = false)
    private String productInformation;

    @Column(nullable = false)
    private String productCreatedTime;

    @Column(length = 50)
    private String productFileName;

    @Column
    private Long categoryId;


    @ColumnDefault("0")
    @Column(nullable = false)
    private int productHits;
    
    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartProductEntity> cartProductEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WishProductEntity> wishProductEntityList = new ArrayList<>();


    public static ProductEntity toSaveEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCounts(productDTO.getProductCounts());
        productEntity.setProductAdmin(productDTO.getProductAdmin());
        productEntity.setProductBrand(productDTO.getProductBrand());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setProductInformation(productDTO.getProductInformation());
        productEntity.setProductCreatedTime(productDTO.getProductCreatedTime());
        productEntity.setProductFileName(productDTO.getProductFileName());
        return productEntity;
    }

    public void setProductEntity(Object o) {
    }
}
