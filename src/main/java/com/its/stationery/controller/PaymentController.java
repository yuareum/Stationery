package com.its.stationery.controller;

import com.its.stationery.dto.PaymentDTO;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.service.PaymentService;
import com.its.stationery.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    private final ProductService productService;

    @PostMapping("/save")
    public @ResponseBody int save(@ModelAttribute PaymentDTO paymentDTO){
        Long saveResult = paymentService.save(paymentDTO);
        if(saveResult > 0){
            return 1;
        }
        else{
            return 0;
        }
    }
}
