package com.its.stationery.service;

import com.its.stationery.dto.ReviewDTO;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.entity.ReviewEntity;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.ProductRepository;
import com.its.stationery.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    public List<ReviewDTO> findByReviewProductId(Long reviewProductId) {
        List<ReviewEntity> reviewEntityList = reviewRepository.findByReviewProductId(reviewProductId);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for(ReviewEntity review: reviewEntityList){
            reviewDTOList.add(ReviewDTO.toReviewDTO(review));
        }
        return reviewDTOList;
    }

    public Long save(ReviewDTO reviewDTO) throws IOException {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(reviewDTO.getReviewWriter());
        if(optionalMemberEntity.isPresent()){
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(reviewDTO.getReviewProductId());
            if(optionalProductEntity.isPresent()){
                MultipartFile reviewFile = reviewDTO.getReviewFile();
                String reviewFileName = reviewFile.getOriginalFilename();
                reviewFileName = System.currentTimeMillis() + "_" + reviewFileName;
                String savePath = "C:\\springboot_img\\" + reviewFileName;
                if(!reviewFile.isEmpty()){
                    reviewFile.transferTo(new File(savePath));
                }
                reviewDTO.setReviewFileName(reviewFileName);
                Long saveId = reviewRepository.save(ReviewEntity.toSaveEntity(reviewDTO, optionalMemberEntity.get(),optionalProductEntity.get())).getId();
                return saveId;
            }
        }
        return null;
    }

    public Long findByReviewWriterAndReviewProductId(String reviewWriter, Long reviewProductId) {
        Optional<ReviewEntity> optionalReviewEntity = reviewRepository.findByReviewWriterAndReviewProductId(reviewWriter, reviewProductId);
        if(optionalReviewEntity.isPresent()){
            ReviewDTO reviewDTO = ReviewDTO.toReviewDTO(optionalReviewEntity.get());
            return reviewDTO.getId();
        }
        else {
            return null;
        }
    }

    public ReviewDTO findById(Long id) {
        Optional<ReviewEntity> optionalReviewEntity = reviewRepository.findById(id);
        if(optionalReviewEntity.isPresent()){
            ReviewDTO reviewDTO = ReviewDTO.toReviewDTO(optionalReviewEntity.get());
            return reviewDTO;
        }
        return null;
    }

    public List<ReviewDTO> findByReviewWriter(String reviewWriter) {
        List<ReviewEntity> reviewEntityList = reviewRepository.findByReviewWriter(reviewWriter);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for(ReviewEntity reviewEntity : reviewEntityList){
            reviewDTOList.add(ReviewDTO.toReviewDTO(reviewEntity));
        }
        return reviewDTOList;
    }


}
