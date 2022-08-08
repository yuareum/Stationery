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
    private int commentCheck;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String inquiryProductName;

    public static InquiryDTO toInquiryDTO(InquiryEntity inquiryEntity) {
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setId(inquiryEntity.getId());
        inquiryDTO.setInquiryContents(inquiryEntity.getInquiryContents());
        inquiryDTO.setInquiryTitle(inquiryEntity.getInquiryTitle());
        inquiryDTO.setInquiryProductName(inquiryEntity.getInquiryProductName());
        inquiryDTO.setInquiryPublic(inquiryEntity.getInquiryPublic());
        inquiryDTO.setInquiryWriter(inquiryEntity.getInquiryWriter());
        inquiryDTO.setCreatedTime(inquiryEntity.getCreatedTime());
        inquiryDTO.setUpdatedTime(inquiryEntity.getUpdatedTime());
        inquiryDTO.setCommentCheck(inquiryEntity.getCommentCheck());
        inquiryDTO.setInquiryProductId(inquiryEntity.getInquiryProductId());
        return inquiryDTO;

    }


}
