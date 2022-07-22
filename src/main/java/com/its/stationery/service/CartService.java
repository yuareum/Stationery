package com.its.stationery.service;

import com.its.stationery.dto.CartDTO;
import com.its.stationery.dto.CartProductDTO;
import com.its.stationery.entity.CartEntity;
import com.its.stationery.entity.CartProductEntity;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.repository.CartProductRepository;
import com.its.stationery.repository.CartRepository;
import com.its.stationery.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    public Long save(String memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        if(optionalMemberEntity.isPresent()){
            if(!Objects.equals(memberId, "admin")){
                Long saveId = cartRepository.save(CartEntity.toSaveEntity(optionalMemberEntity.get())).getId();
                return saveId;
            }
            return null;
        }
        return null;
    }

    public CartDTO findByMemberId(String cartMemberId) {
        Optional<CartEntity> optionalCartEntity = cartRepository.findByMemberId(cartMemberId);
        if(optionalCartEntity.isPresent()) {
            return CartDTO.toCartDTO(optionalCartEntity.get());
        }
        return null;
    }
}
