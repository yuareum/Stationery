package com.its.stationery.repository;

import com.its.stationery.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    @Modifying
    @Query(value = "update NoticeEntity n set n.noticeHits= n.noticeHits+1 where n.id= :id")
    void noticeHits(@Param("id") Long id);
}
