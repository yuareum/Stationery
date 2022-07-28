package com.its.stationery.entity;

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


    public static WishEntity toSaveEntity(MemberEntity memberEntity) {
        WishEntity wishEntity = new WishEntity();
        wishEntity.setWishMemberId(memberEntity.getMemberId());
        wishEntity.setMemberEntity(memberEntity);
        return wishEntity;
    }

}
