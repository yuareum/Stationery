package com.its.stationery.service;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.MemberDTO;
import com.its.stationery.entity.CartEntity;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.WishEntity;
import com.its.stationery.repository.CartRepository;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final WishRepository wishRepository;

    public Long save(MemberDTO memberDTO) throws IOException {
        MultipartFile memberProfile = memberDTO.getMemberProfile();
        String memberProfileName = memberProfile.getOriginalFilename();
        memberProfileName = System.currentTimeMillis() + "_" + memberProfileName;
        String savePath = "C:\\springboot_img\\" + memberProfileName;
        if(!memberProfile.isEmpty()){
            memberProfile.transferTo(new File(savePath));
        }
        memberDTO.setMemberProfileName(memberProfileName);
        Long saveId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO)).getId();
        return saveId;
    }
    public MemberDTO findById(Long id){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            //           return MemberDTO.toMemberDTO(optionalMemberEntity.get());
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return  memberDTO;
        }
        else{
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity member: memberEntityList){
            memberDTOList.add(MemberDTO.toMemberDTO(member));
        }
        return memberDTOList;
    }

    public String dupCheck(String memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        if(optionalMemberEntity.isEmpty()){
            return "ok";
        }
        else{
            return "no";
        }
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberDTO.getMemberId());
        if(optionalMemberEntity.isPresent()){
            MemberEntity loginEntity = optionalMemberEntity.get();
            if(loginEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                return MemberDTO.toMemberDTO(loginEntity);
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }

    }

    public Long update(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toUpdateEntity(memberDTO);
        Long id = memberRepository.save(memberEntity).getId();
        return id;
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }


    public MemberDTO findByMemberId(String memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return  memberDTO;
        }
        else{
            return null;
        }
    }

    public Page<MemberDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1;
        // 삼항연산자
        page = (page == 1)? 0: (page-1);
        Page<MemberEntity> memberEntities = memberRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<MemberDTO> memberList = memberEntities.map(
                member -> new MemberDTO(member.getId(),
                        member.getMemberId(),
                        member.getMemberName(),
                        member.getMemberEmail(),
                        member.getMemberMobile()
                ));
        return memberList;
    }
}
