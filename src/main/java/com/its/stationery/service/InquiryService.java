package com.its.stationery.service;

import com.its.stationery.dto.InquiryDTO;
import com.its.stationery.entity.InquiryEntity;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.repository.InquiryRepository;
import com.its.stationery.repository.MemberRepository;
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

    @Transactional
    public List<InquiryDTO> findByInquiryProductId(Long inquiryProductId) {
        List<InquiryEntity> inquiryEntityList = inquiryRepository.findByInquiryProductId(inquiryProductId);
        List<InquiryDTO> inquiryDTOList = new ArrayList<>();
        for(InquiryEntity inquiry : inquiryEntityList){
            if(inquiry.getInquiryPublic() == 1){
                inquiryDTOList.add(InquiryDTO.toInquiryDTO(inquiry));
            }
        }
        return inquiryDTOList;
    }

    public Long save(InquiryDTO inquiryDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(inquiryDTO.getInquiryWriter());
        if(optionalMemberEntity.isPresent()){
            InquiryEntity inquiryEntity = InquiryEntity.toInquiryEntity(inquiryDTO,optionalMemberEntity.get());
            Long id = inquiryRepository.save(inquiryEntity).getId();
            return id;
        }
        return null;
    }
}
