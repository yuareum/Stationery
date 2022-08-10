package com.its.stationery.dto;

import com.its.stationery.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long reviewProductId;
    private String reviewProductName;
    private String reviewWriter;
    private String reviewTitle;
    private String reviewContents;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private MultipartFile reviewFile;
    private String reviewFileName;

    public ReviewDTO(Long id, Long reviewProductId, String reviewProductName, String reviewFileName, String reviewTitle, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.reviewProductId = reviewProductId;
        this.reviewProductName = reviewProductName;
        this.reviewFileName = reviewFileName;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.reviewTitle = reviewTitle;
    }

    public static ReviewDTO toReviewDTO(ReviewEntity reviewEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(reviewEntity.getId());
        reviewDTO.setReviewTitle(reviewEntity.getReviewTitle());
        reviewDTO.setReviewProductId(reviewEntity.getReviewProductId());
        reviewDTO.setReviewProductName(reviewEntity.getReviewProductName());
        reviewDTO.setReviewContents(reviewEntity.getReviewContents());
        reviewDTO.setReviewWriter(reviewEntity.getReviewWriter());
        reviewDTO.setReviewFileName(reviewEntity.getReviewFileName());
        reviewDTO.setCreatedTime(reviewEntity.getCreatedTime());
        reviewDTO.setUpdatedTime(reviewEntity.getUpdatedTime());
        return reviewDTO;
    }
}
