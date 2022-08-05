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
public class InquiryEntity {
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

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime inquiryCreatedTime;

    @Column(nullable = false)
    private Long inquiryProductId;

    @Column(nullable = false)
    private int inquiryPublic;

    @ColumnDefault("0")
    @Column(nullable = false)
    private int commentCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "inquiryEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntity = new ArrayList<>();


    public static InquiryEntity toInquiryEntity(InquiryDTO inquiryDTO, MemberEntity memberEntity) {
        InquiryEntity inquiryEntity = new InquiryEntity();
        inquiryEntity.setInquiryProductId(inquiryDTO.getInquiryProductId());
        inquiryEntity.setInquiryWriter(inquiryDTO.getInquiryWriter());
        inquiryEntity.setInquiryTitle(inquiryDTO.getInquiryTitle());
        inquiryEntity.setInquiryContents(inquiryDTO.getInquiryContents());
        inquiryEntity.setInquiryPublic(inquiryDTO.getInquiryPublic());
        inquiryEntity.setMemberEntity(memberEntity);
        return inquiryEntity;
    }
}
