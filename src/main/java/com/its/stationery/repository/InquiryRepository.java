package com.its.stationery.repository;

import com.its.stationery.entity.InquiryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
    List<InquiryEntity> findByInquiryProductId(Long inquiryProductId);

    List<InquiryEntity> findByInquiryWriter(String inquiryMemberId);

    Page<InquiryEntity> findByInquiryWriterContainingIgnoreCase(String inquiryWriter, Pageable pageable);
}
