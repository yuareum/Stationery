package com.its.stationery.entity;

import com.its.stationery.dto.NoticeDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "notice_table")
public class NoticeEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 20, nullable = false)
    private String noticeWriter;

    @Column(length = 50, nullable = false)
    private String noticeTitle;

    @Column(length = 500, nullable = false)
    private String noticeContents;

    @Column(length = 50)
    private String noticeFileName;

    @ColumnDefault("0")
    @Column(nullable = false)
    private int noticeHits;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;


    public static NoticeEntity toSaveEntity(NoticeDTO noticeDTO,MemberEntity memberEntity) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeFileName(noticeDTO.getNoticeFileName());
        noticeEntity.setMemberEntity(memberEntity);
        return noticeEntity;
    }
}

