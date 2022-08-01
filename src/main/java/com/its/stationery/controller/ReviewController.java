package com.its.stationery.controller;

import com.its.stationery.dto.ProductDTO;
import com.its.stationery.dto.ReviewDTO;
import com.its.stationery.service.ProductService;
import com.its.stationery.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ProductService productService;
    @GetMapping("/save-form/{reviewProductId}")
    public String saveForm(@PathVariable("reviewProductId") Long reviewProductId, Model model){
        ProductDTO productDTO = productService.findById(reviewProductId);
        model.addAttribute("product", productDTO);
        return "reviewPages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ReviewDTO reviewDTO){
        return "redirect:/product/" + reviewDTO.getReviewProductId();
    }
}
