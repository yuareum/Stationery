package com.its.stationery.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "notice")
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
    private int noticeHits;

}

