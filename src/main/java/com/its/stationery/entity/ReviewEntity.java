package com.its.stationery.entity;

import com.its.stationery.dto.ReviewDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "review_table")
public class ReviewEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long reviewProductId;

    @Column(length = 50, nullable = false)
    private String reviewProductName;

    @Column(length = 20, nullable = false)
    private String reviewWriter;

    @Column(length = 50, nullable = false)
    private String reviewTitle;

    @Column(length = 200, nullable = false)
    private String reviewContents;

    @Column(length=50)
    private String reviewFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;


    public static ReviewEntity toSaveEntity(ReviewDTO reviewDTO, MemberEntity memberEntity, ProductEntity productEntity) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewProductId(reviewDTO.getReviewProductId());
        reviewEntity.setReviewWriter(reviewDTO.getReviewWriter());
        reviewEntity.setReviewTitle(reviewDTO.getReviewTitle());
        reviewEntity.setReviewContents(reviewDTO.getReviewContents());
        reviewEntity.setReviewProductName(reviewDTO.getReviewProductName());
        reviewEntity.setReviewFileName(reviewDTO.getReviewFileName());
        reviewEntity.setMemberEntity(memberEntity);
        reviewEntity.setProductEntity(productEntity);
        return reviewEntity;
    }
}
