package com.its.stationery.controller;

import com.its.stationery.dto.ProductDTO;
import com.its.stationery.dto.ReviewDTO;
import com.its.stationery.service.OrderService;
import com.its.stationery.service.ProductService;
import com.its.stationery.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ProductService productService;

    @GetMapping("/save-form/{reviewProductId}")
    public String saveForm(@PathVariable("reviewProductId") Long reviewProductId, Model model, HttpSession session){
        Long checkResult = reviewService.findByReviewWriterAndReviewProductId((String) session.getAttribute("loginMemberId"), reviewProductId);
        if (checkResult == null) {
            ProductDTO productDTO = productService.findById(reviewProductId);
            model.addAttribute("product", productDTO);
            return "reviewPages/save";
        }
        else{
            return "redirect:/product/" + reviewProductId;
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ReviewDTO reviewDTO) throws IOException {
        Long id = reviewService.save(reviewDTO);
        return "redirect:/review/" + id;
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        ReviewDTO reviewDTO = reviewService.findById(id);
        model.addAttribute("updateReview",reviewDTO);
        return "reviewPages/update";
    }

    @GetMapping("/{reviewWriter}")
    public String findByMemberId(@PathVariable("reviewWriter") String reviewWriter, Model model){
        List<ReviewDTO> reviewDTOList = reviewService.findByReviewWriter(reviewWriter);
        model.addAttribute("reviewList", reviewDTOList);
        return "reviewPages/list";
    }

}
