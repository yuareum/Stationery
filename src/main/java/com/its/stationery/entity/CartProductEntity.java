package com.its.stationery.entity;

import com.its.stationery.dto.CartProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "cart_product_table")
public class CartProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20)
    private String cartMemberId;

    @Column(length = 50)
    private String cartProductFileName;

    @Column
    private Long cartProductId;

    @Column(length = 50, nullable = false)
    private String cartProductName;

    @Column(length = 30, nullable = false)
    private String cartProductBrand;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private Long cartProductPrice;

    @Column(nullable = false)
    private int cartProductCounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    public static CartProductEntity toCartProductEntity(CartProductDTO cartProductDTO, CartEntity cartEntity, ProductEntity productEntity) {
        CartProductEntity cartProductEntity = new CartProductEntity();
        cartProductEntity.setCartMemberId(cartProductDTO.getCartMemberId());
        cartProductEntity.setCartProductBrand(cartProductDTO.getCartProductBrand());
        cartProductEntity.setCartProductCounts(cartProductDTO.getCartProductCounts());
        cartProductEntity.setCartProductId(cartProductDTO.getCartProductId());
        cartProductEntity.setCartProductName(cartProductDTO.getCartProductName());
        cartProductEntity.setCartProductPrice(cartProductDTO.getCartProductPrice());
        cartProductEntity.setCartProductFileName(cartProductDTO.getCartProductFileName());

        cartProductEntity.setCartEntity(cartEntity);
        cartProductEntity.setProductEntity(productEntity);
        return cartProductEntity;
    }
}
