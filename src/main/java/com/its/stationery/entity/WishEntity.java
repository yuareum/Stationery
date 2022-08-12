package com.its.stationery.entity;

import com.its.stationery.dto.WishDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "wish_table")
public class WishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20, nullable = false)
    private String wishMemberId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "wishEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WishProductEntity> wishProductEntityList = new ArrayList<>();


    public static WishEntity toSaveEntity(MemberEntity memberEntity,String wishMemberId) {
        WishEntity wishEntity = new WishEntity();
        wishEntity.setWishMemberId(wishMemberId);
        wishEntity.setMemberEntity(memberEntity);
        return wishEntity;
    }

    public static WishEntity toUpdateEntity(MemberEntity memberEntity, WishDTO wishDTO) {
        WishEntity wishEntity = new WishEntity();
        wishEntity.setId(wishDTO.getId());
        wishEntity.setWishMemberId(wishDTO.getWishMemberId());
        wishEntity.setMemberEntity(memberEntity);
        return wishEntity;
    }
}
