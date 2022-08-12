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

    @Column(length = 20, nullable = false)
    private String categoryName;

    @ColumnDefault("0")
    @Column(nullable = false)
    private int productHits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InquiryEntity> inquiryEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<CartProductEntity> cartProductEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<WishProductEntity> wishProductEntityList = new ArrayList<>();


    public static ProductEntity toSaveEntity(ProductDTO productDTO,MemberEntity memberEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCounts(productDTO.getProductCounts());
        productEntity.setProductAdmin(productDTO.getProductAdmin());
        productEntity.setProductBrand(productDTO.getProductBrand());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setCategoryName(productDTO.getCategoryName());
        productEntity.setProductInformation(productDTO.getProductInformation());
        productEntity.setProductCreatedTime(productDTO.getProductCreatedTime());
        productEntity.setProductFileName(productDTO.getProductFileName());
        productEntity.setMemberEntity(memberEntity);
        return productEntity;
    }

    public static ProductEntity toUpdateEntity(ProductDTO productDTO, MemberEntity memberEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductInformation(productDTO.getProductInformation());
        productEntity.setProductBrand(productDTO.getProductBrand());
        productEntity.setProductAdmin(productDTO.getProductAdmin());
        productEntity.setProductCreatedTime(productDTO.getProductCreatedTime());
        productEntity.setProductCounts(productDTO.getProductCounts());
        productEntity.setCategoryName(productDTO.getCategoryName());
        productEntity.setProductHits(productDTO.getProductHits());
        productEntity.setProductFileName(productDTO.getProductFileName());
        productEntity.setMemberEntity(memberEntity);
        return productEntity;
    }

    public static ProductEntity toUpdateCounts(ProductDTO productDTO,int counts, MemberEntity memberEntity){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setProductHits(productDTO.getProductHits());
        productEntity.setProductCounts(productDTO.getProductCounts()-counts);
        productEntity.setProductAdmin(productDTO.getProductAdmin());
        productEntity.setProductBrand(productDTO.getProductBrand());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductInformation(productDTO.getProductInformation());
        productEntity.setProductFileName(productDTO.getProductFileName());
        productEntity.setCategoryName(productDTO.getCategoryName());
        productEntity.setProductCreatedTime(productDTO.getProductCreatedTime());
        productEntity.setProductPrice(productDTO.getProductPrice());
        productEntity.setMemberEntity(memberEntity);
        return productEntity;
    }

}
