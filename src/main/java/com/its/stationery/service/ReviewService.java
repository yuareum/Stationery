package com.its.stationery.service;

import com.its.stationery.dto.ReviewDTO;
import com.its.stationery.entity.ReviewEntity;
import com.its.stationery.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<ReviewDTO> findByReviewProductId(Long reviewProductId) {
        List<ReviewEntity> reviewEntityList = reviewRepository.findByReviewProductId(reviewProductId);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for(ReviewEntity review: reviewEntityList){
            reviewDTOList.add(ReviewDTO.toReviewDTO(review));
        }
        return reviewDTOList;
    }
}
