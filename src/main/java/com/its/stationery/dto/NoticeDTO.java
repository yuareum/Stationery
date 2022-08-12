package com.its.stationery.dto;

import com.its.stationery.entity.NoticeEntity;
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


    public NoticeDTO(Long id, String noticeWriter, String noticeTitle, LocalDateTime createdTime, int noticeHits) {
        this.id = id;
        this.noticeWriter = noticeWriter;
        this.noticeTitle = noticeTitle;
        this.createdTime = createdTime;
        this.noticeHits = noticeHits;
    }

    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(noticeEntity.getId());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setNoticeWriter(noticeEntity.getNoticeWriter());
        noticeDTO.setNoticeContents(noticeEntity.getNoticeContents());
        noticeDTO.setNoticeFileName(noticeEntity.getNoticeFileName());
        noticeDTO.setNoticeHits(noticeEntity.getNoticeHits());
        noticeDTO.setCreatedTime(noticeEntity.getCreatedTime());
        noticeDTO.setUpdatedTime(noticeEntity.getCreatedTime());
        return noticeDTO;
    }
}
