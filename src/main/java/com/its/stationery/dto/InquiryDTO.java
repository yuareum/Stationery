package com.its.stationery.dto;

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
    private LocalDateTime inquiryCreatedTime;
    private int commentCheck;
}
