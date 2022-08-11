package com.its.stationery.dto;

import com.its.stationery.entity.InquiryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDTO {
    private Long id;
    private String inquiryWriter;
    private String inquiryTitle;
    private String inquiryContents;
    private Long inquiryProductId;
    private int inquiryPublic;
    private LocalDateTime createdTime;
    private String inquiryProductName;

    private int inquiryHits;

    public InquiryDTO(Long id, String inquiryWriter, String inquiryTitle, int inquiryPublic, Long inquiryProductId,  String inquiryProductName, LocalDateTime createdTime) {
        this.id = id;
        this.inquiryWriter = inquiryWriter;
        this.inquiryProductId = inquiryProductId;
        this.inquiryPublic = inquiryPublic;
        this.inquiryTitle = inquiryTitle;
        this.inquiryProductName = inquiryProductName;
        this.createdTime = createdTime;
    }

    public InquiryDTO(Long id, String inquiryWriter, String inquiryTitle, String inquiryProductName, LocalDateTime createdTime) {
        this.id = id;
        this.inquiryWriter = inquiryWriter;
        this.inquiryTitle = inquiryTitle;
        this.createdTime = createdTime;
        this.inquiryProductName = inquiryProductName;
    }

    public static InquiryDTO toInquiryDTO(InquiryEntity inquiryEntity) {
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setId(inquiryEntity.getId());
        inquiryDTO.setInquiryContents(inquiryEntity.getInquiryContents());
        inquiryDTO.setInquiryTitle(inquiryEntity.getInquiryTitle());
        inquiryDTO.setInquiryProductName(inquiryEntity.getInquiryProductName());
        inquiryDTO.setInquiryPublic(inquiryEntity.getInquiryPublic());
        inquiryDTO.setInquiryWriter(inquiryEntity.getInquiryWriter());
        inquiryDTO.setCreatedTime(inquiryEntity.getCreatedTime());
        inquiryDTO.setInquiryHits(inquiryEntity.getInquiryHits());
        inquiryDTO.setInquiryProductId(inquiryEntity.getInquiryProductId());
        return inquiryDTO;

    }


}
