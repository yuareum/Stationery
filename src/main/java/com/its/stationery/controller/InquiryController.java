package com.its.stationery.controller;

import com.its.stationery.dto.InquiryDTO;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.service.InquiryService;
import com.its.stationery.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findByInquiryWriter/{inquiryWriter}")
    public String findByInquiryWriter(@PathVariable("inquiryWriter") String inquiryWriter, Model model){
        List<InquiryDTO> inquiryDTOList = inquiryService.findByInquiryWriter(inquiryWriter);
        model.addAttribute("inquiryList" , inquiryDTOList);
        return "inquiryPages/myList";
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(@PathVariable("id") Long id){
        InquiryDTO inquiryDTO = inquiryService.findById(id);
        if(inquiryDTO.getCommentCheck() == 0){
            return "/inquiryPages/update";
        }
        return "redirect:/findByInquiryWriter/" + inquiryDTO.getInquiryWriter();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        inquiryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        InquiryDTO inquiryDTO = inquiryService.findById(id);
        model.addAttribute("inquiry", inquiryDTO);
        return "inquiryPages/detail";
    }
}
