package com.its.stationery.repository;

import com.its.stationery.entity.InquiryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
    List<InquiryEntity> findByInquiryProductId(Long inquiryProductId);

    List<InquiryEntity> findByInquiryWriter(String inquiryMemberId);

    Page<InquiryEntity> findByInquiryWriterContainingIgnoreCase(String inquiryWriter, Pageable pageable);

    @Modifying
    @Query(value = "update InquiryEntity i set i.inquiryHits= i.inquiryHits+1 where i.id= :id")
    void inquiryHits(@Param("id") Long id);
}
