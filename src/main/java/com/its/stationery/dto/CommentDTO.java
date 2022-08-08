package com.its.stationery.dto;

import com.its.stationery.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Long commentInquiryId;
    private String commentWriter;
    private String commentContents;
    private LocalDateTime commentCreatedTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentInquiryId(commentEntity.getCommentInquiryId());
        commentDTO.setCommentCreatedTime(commentEntity.getCommentCreatedTime());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        return commentDTO;
    }
}
