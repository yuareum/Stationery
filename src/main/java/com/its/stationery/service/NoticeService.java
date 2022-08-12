package com.its.stationery.service;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.MemberDTO;
import com.its.stationery.dto.NoticeDTO;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.NoticeEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    public Page<NoticeDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1)? 0: (page-1);
        Page<NoticeEntity> noticeEntities = noticeRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<NoticeDTO> noticeList = noticeEntities.map(
                notice -> new NoticeDTO(notice.getId(),
                        notice.getNoticeWriter(),
                        notice.getNoticeTitle(),
                        notice.getCreatedTime(),
                        notice.getNoticeHits()
                ));
        return noticeList;
    }

    public Long save(NoticeDTO noticeDTO) throws IOException {
        MultipartFile noticeFile = noticeDTO.getNoticeFile();
        String noticeFileName = noticeFile.getOriginalFilename();
        noticeFileName = System.currentTimeMillis() + "_" + noticeFileName;
        String savePath = "C:\\springboot_img\\" + noticeFileName;
        if(!noticeFile.isEmpty()){
            noticeFile.transferTo(new File(savePath));
        }
        noticeDTO.setNoticeFileName(noticeFileName);
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(noticeDTO.getNoticeWriter());
        if(optionalMemberEntity.isPresent()){
            Long saveId = noticeRepository.save(NoticeEntity.toSaveEntity(noticeDTO,optionalMemberEntity.get())).getId();
            return saveId;
        }
        return null;
    }
    @Transactional
    public NoticeDTO detail(Long id) {
        noticeRepository.noticeHits(id);
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(id);
        if(optionalNoticeEntity.isPresent()){
            return NoticeDTO.toNoticeDTO(optionalNoticeEntity.get());
        }
        else {
            return null;
        }
    }

    public NoticeDTO findById(Long id) {
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(id);
        if(optionalNoticeEntity.isPresent()){
            NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(optionalNoticeEntity.get());
            return noticeDTO;
        }
        return null;
    }

    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    public Long update(NoticeDTO noticeDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(noticeDTO.getNoticeWriter());
        if(optionalMemberEntity.isPresent()){
            NoticeEntity noticeEntity = NoticeEntity.toUpdateEntity(noticeDTO,optionalMemberEntity.get());
            Long id = noticeRepository.save(noticeEntity).getId();
            return id;
        }
        return null;
    }
}
