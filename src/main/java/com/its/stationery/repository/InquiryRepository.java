package com.its.stationery.repository;

import com.its.stationery.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
}
