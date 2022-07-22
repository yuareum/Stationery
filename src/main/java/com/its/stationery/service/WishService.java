package com.its.stationery.service;

import com.its.stationery.entity.CartEntity;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.WishEntity;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    private final MemberRepository memberRepository;

    public Long save(String memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        if(optionalMemberEntity.isPresent()){
            if(!Objects.equals(memberId, "admin")){
                Long saveId = wishRepository.save(WishEntity.toSaveEntity(memberId,optionalMemberEntity.get())).getId();
                return saveId;
            }
            return null;
        }
        return null;
    }
}
