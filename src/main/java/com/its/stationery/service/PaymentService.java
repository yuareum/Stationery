package com.its.stationery.service;

import com.its.stationery.dto.PaymentDTO;
import com.its.stationery.entity.MemberEntity;
import com.its.stationery.entity.PaymentEntity;
import com.its.stationery.repository.MemberRepository;
import com.its.stationery.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    private final MemberRepository memberRepository;

    public Long save(PaymentDTO paymentDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(paymentDTO.getPaymentMemberId());
        if(optionalMemberEntity.isPresent()){
            Long saveId = paymentRepository.save(PaymentEntity.toSaveEntity(paymentDTO,optionalMemberEntity.get())).getId();
            return saveId;
        }
        return null;
    }
}
