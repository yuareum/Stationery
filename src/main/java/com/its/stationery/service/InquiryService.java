package com.its.stationery.service;

import com.its.stationery.dto.InquiryDTO;
import com.its.stationery.entity.InquiryEntity;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.ProductEntity;
import com.its.stationery.repository.InquiryRepository;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
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

    public List<InquiryDTO> findByInquiryWriter(String inquiryWriter) {
        List<InquiryEntity> inquiryEntityList = inquiryRepository.findByInquiryWriter(inquiryWriter);
        List<InquiryDTO> inquiryDTOList = new ArrayList<>();
        for(InquiryEntity inquiry : inquiryEntityList){
            inquiryDTOList.add(InquiryDTO.toInquiryDTO(inquiry));
        }
        return inquiryDTOList;
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
}

