package com.its.stationery.entity;

import com.its.stationery.dto.InquiryDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "inquiry_table")
public class InquiryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20, nullable = false)
    private String inquiryWriter;

    @Column(length = 50, nullable = false)
    private String inquiryTitle;

    @Column(length = 500, nullable = false)
    private String inquiryContents;

    @Column(nullable = false)
    private Long inquiryProductId;

    @Column(length = 50, nullable = false)
    private String inquiryProductName;


    @Column(nullable = false)
    private int inquiryPublic;

    @ColumnDefault("0")
    @Column(nullable = false)
    private int commentCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @OneToMany(mappedBy = "inquiryEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntity = new ArrayList<>();


    public static InquiryEntity toSaveEntity(InquiryDTO inquiryDTO, MemberEntity memberEntity,ProductEntity productEntity) {
        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setInquiryProductId(inquiryDTO.getInquiryProductId());
        inquiryEntity.setInquiryWriter(inquiryDTO.getInquiryWriter());
        inquiryEntity.setInquiryTitle(inquiryDTO.getInquiryTitle());
        inquiryEntity.setInquiryContents(inquiryDTO.getInquiryContents());
        inquiryEntity.setInquiryPublic(inquiryDTO.getInquiryPublic());
        inquiryEntity.setInquiryProductName(inquiryDTO.getInquiryProductName());
        inquiryEntity.setMemberEntity(memberEntity);
        inquiryEntity.setProductEntity(productEntity);
        return inquiryEntity;
    }
}
