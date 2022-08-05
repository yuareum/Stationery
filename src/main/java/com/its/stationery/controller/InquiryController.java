package com.its.stationery.controller;

import com.its.stationery.dto.InquiryDTO;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.service.InquiryService;
import com.its.stationery.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inquiry")
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;
    private final ProductService productService;

    @GetMapping("/save-form/{inquiryProductId}")
    public String saveForm(@PathVariable("inquiryProductId") Long inquiryProductId, Model model){
        ProductDTO productDTO = productService.findById(inquiryProductId);
        model.addAttribute("product", productDTO);
        return "inquiryPages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute InquiryDTO inquiryDTO){
        inquiryService.save(inquiryDTO);
        return "redirect:/product/" + inquiryDTO.getInquiryProductId();
    }
}
