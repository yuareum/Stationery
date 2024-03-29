package com.its.stationery.service;

import com.its.stationery.dto.CartDTO;
import com.its.stationery.dto.WishDTO;
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

    public Long save(String wishMemberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(wishMemberId);
        if(optionalMemberEntity.isPresent()){
            if(!Objects.equals(wishMemberId, "admin")){
                Long saveId = wishRepository.save(WishEntity.toSaveEntity(optionalMemberEntity.get(),wishMemberId)).getId();
                return saveId;
            }
        }
        return null;
    }

    public WishDTO findByWishMemberId(String wishMemberId) {
        Optional<WishEntity> optionalWishEntity = wishRepository.findByWishMemberId(wishMemberId);
        if(optionalWishEntity.isPresent()){
            return WishDTO.toWishDTO(optionalWishEntity.get());
        }
        return null;
    }
}
