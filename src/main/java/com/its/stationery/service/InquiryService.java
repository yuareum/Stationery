package com.its.stationery.service;

import com.its.stationery.dto.InquiryDTO;
import com.its.stationery.entity.InquiryEntity;
import com.its.stationery.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;

    public List<InquiryDTO> findByInquiryProductId(Long inquiryProductId) {
        List<InquiryEntity> inquiryEntityList = inquiryRepository.findByInquiryProductId(inquiryProductId);
        List<InquiryDTO> inquiryDTOList = new ArrayList<>();
        for(InquiryEntity inquiry : inquiryEntityList){
            inquiryDTOList.add(InquiryDTO.toInquiryDTO(inquiry));
        }
        return inquiryDTOList;
    }
}
