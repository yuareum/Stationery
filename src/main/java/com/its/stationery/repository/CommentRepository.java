package com.its.stationery.repository;

import com.its.stationery.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByCommentInquiryId(Long commentInquiryId);
}
