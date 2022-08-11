package com.its.stationery.entity;

import com.its.stationery.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "comment_table")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(length = 500, nullable = false)
    private String commentContents;

    @Column(nullable = false)
    private Long commentInquiryId;


    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime commentCreatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private InquiryEntity inquiryEntity;


    public static CommentEntity toSaveEntity(CommentDTO commentDTO, InquiryEntity inquiryEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setCommentInquiryId(commentDTO.getCommentInquiryId());
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setInquiryEntity(inquiryEntity);
        return commentEntity;
    }

}
