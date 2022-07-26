package com.its.stationery.service;

import com.its.stationery.dto.PaymentDTO;
import com.its.stationery.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public void save(PaymentDTO paymentDTO) {
    }
}
