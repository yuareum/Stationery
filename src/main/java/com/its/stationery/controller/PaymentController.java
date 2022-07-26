package com.its.stationery.controller;

import com.its.stationery.dto.PaymentDTO;
import com.its.stationery.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @PostMapping("/save")
    public String save(@ModelAttribute PaymentDTO paymentDTO){
        paymentService.save(paymentDTO);
        return "redirect:/order/findByMemberId"+ paymentDTO.getPaymentMemberId();
    }
}
