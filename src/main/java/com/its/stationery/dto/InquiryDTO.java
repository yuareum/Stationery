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
    private LocalDateTime inquiryCreatedTime;
    private int commentCheck;

    public static InquiryDTO toInquiryDTO(InquiryEntity inquiryEntity) {
        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setId(inquiryEntity.getId());
        inquiryDTO.setInquiryContents(inquiryEntity.getInquiryContents());
        inquiryDTO.setInquiryTitle(inquiryEntity.getInquiryTitle());
        inquiryDTO.setInquiryWriter(inquiryEntity.getInquiryWriter());
        inquiryDTO.setInquiryCreatedTime(inquiryEntity.getInquiryCreatedTime());
        inquiryDTO.setCommentCheck(inquiryEntity.getCommentCheck());
        inquiryDTO.setInquiryProductId(inquiryEntity.getInquiryProductId());
        return inquiryDTO;

    }
}
