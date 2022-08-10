package com.its.stationery.service;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.ReviewDTO;
import com.its.stationery.dto.WishProductDTO;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.entity.ReviewEntity;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.ProductRepository;
import com.its.stationery.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.ldap.PagedResultsControl;
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

    public Page<ReviewDTO> findByReviewWriter(String reviewWriter, Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);
        Page<ReviewEntity> reviewEntity = null;
        reviewRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.Direction.DESC, "id"));
        reviewEntity = reviewRepository.findByReviewWriterContainingIgnoreCase(reviewWriter, PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<ReviewDTO> reviewList = reviewEntity.map(
                review -> new ReviewDTO(review.getId(),
                        review.getReviewProductId(),
                        review.getReviewProductName(),
                        review.getReviewFileName(),
                        review.getReviewTitle(),
                        review.getCreatedTime(),
                        review.getUpdatedTime()
                ));
        return reviewList;
    }


    public void update(ReviewDTO reviewDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(reviewDTO.getReviewWriter());
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(reviewDTO.getReviewProductId());
        if(optionalMemberEntity.isPresent()){
            if (optionalProductEntity.isPresent()){
                ReviewEntity reviewEntity = ReviewEntity.toUpdateEntity(reviewDTO,optionalMemberEntity.get(),optionalProductEntity.get());
                reviewRepository.save(reviewEntity).getId();
            }
        }
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
