package com.its.stationery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Long id;
    private String noticeWriter;
    private String noticeTitle;
    private String noticeContents;
    private MultipartFile noticeFile;
    private String noticeFileName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private int noticeHits;



}
