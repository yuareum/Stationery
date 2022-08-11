package com.its.stationery.service;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.InquiryDTO;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.dto.ReviewDTO;
import com.its.stationery.entity.InquiryEntity;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.entity.ReviewEntity;
import com.its.stationery.repository.InquiryRepository;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    public List<InquiryDTO> findByInquiryProductId(Long inquiryProductId) {
        List<InquiryEntity> inquiryEntityList = inquiryRepository.findByInquiryProductId(inquiryProductId);
        List<InquiryDTO> inquiryDTOList = new ArrayList<>();
        for (InquiryEntity inquiry : inquiryEntityList) {
            if (inquiry.getInquiryPublic() == 1) {
                inquiryDTOList.add(InquiryDTO.toInquiryDTO(inquiry));
            }
        }
        return inquiryDTOList;
    }

    public Long save(InquiryDTO inquiryDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(inquiryDTO.getInquiryWriter());
        if (optionalMemberEntity.isPresent()) {
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(inquiryDTO.getInquiryProductId());
            if (optionalProductEntity.isPresent()) {
                InquiryEntity inquiryEntity = InquiryEntity.toSaveEntity(inquiryDTO, optionalMemberEntity.get(), optionalProductEntity.get());
                Long id = inquiryRepository.save(inquiryEntity).getId();
                return id;
            }
        }
        return null;
    }

    public Page<InquiryDTO> findByInquiryWriter(String inquiryWriter, Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);
        Page<InquiryEntity> inquiryEntity = null;
        inquiryRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.Direction.DESC, "id"));
        inquiryEntity = inquiryRepository.findByInquiryWriterContainingIgnoreCase(inquiryWriter, PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<InquiryDTO> inquiryList = inquiryEntity.map(
                inquiry -> new InquiryDTO(inquiry.getId(),
                        inquiry.getInquiryWriter(),
                        inquiry.getInquiryTitle(),
                        inquiry.getInquiryPublic(),
                        inquiry.getInquiryProductId(),
                        inquiry.getInquiryProductName(),
                        inquiry.getCreatedTime()
                ));
        return inquiryList;
    }

    public InquiryDTO findById(Long id) {
        Optional<InquiryEntity> optionalInquiryEntity = inquiryRepository.findById(id);
        if(optionalInquiryEntity.isPresent()){
            InquiryDTO inquiryDTO = InquiryDTO.toInquiryDTO(optionalInquiryEntity.get());
            return inquiryDTO;
        }
        return null;
    }

    public void delete(Long id) {
        inquiryRepository.deleteById(id);
    }

    public Long update(InquiryDTO inquiryDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(inquiryDTO.getInquiryWriter());
        if(optionalMemberEntity.isPresent()){
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(inquiryDTO.getInquiryProductId());
            if(optionalProductEntity.isPresent()){
                InquiryEntity inquiryEntity = InquiryEntity.toUpdateEntity(inquiryDTO, optionalMemberEntity.get(),optionalProductEntity.get());
                Long id = inquiryRepository.save(inquiryEntity).getId();
                return id;
            }
        }
        return null;
    }
    @Transactional
    public InquiryDTO detail(Long id) {
        inquiryRepository.inquiryHits(id);
        Optional<InquiryEntity> optionalInquiryEntity = inquiryRepository.findById(id);
        if (optionalInquiryEntity.isPresent()) {
            return InquiryDTO.toInquiryDTO(optionalInquiryEntity.get());
        }
        return null;

    }

    public Page<InquiryDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        page = (page == 1)? 0: (page-1);
        Page<InquiryEntity> inquiryEntities = inquiryRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<InquiryDTO> inquiryList = inquiryEntities.map(
                inquiry -> new InquiryDTO(inquiry.getId(),
                        inquiry.getInquiryWriter(),
                        inquiry.getInquiryTitle(),
                        inquiry.getInquiryProductName(),
                        inquiry.getCreatedTime()
                ));

        return inquiryList;
    }
}

