package com.its.stationery.entity;

import com.its.stationery.dto.CartDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "cart_table")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20, nullable = false)
    private String cartMemberId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartProductEntity> cartProductEntityList = new ArrayList<>();

    public static CartEntity toSaveEntity(MemberEntity memberEntity) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartMemberId(memberEntity.getMemberId());
        cartEntity.setMemberEntity(memberEntity);
        return cartEntity;
    }
}
