package com.its.stationery.controller;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.OrderDTO;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.dto.ReviewDTO;
import com.its.stationery.service.OrderService;
import com.its.stationery.service.ProductService;
import com.its.stationery.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


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

    @GetMapping("/findByReviewWriter/{reviewWriter}")
    public String findByReviewWriter(@PathVariable("reviewWriter") String reviewWriter, Model model, @PageableDefault(page = 1) Pageable pageable){
        Page<ReviewDTO> reviewList = reviewService.findByReviewWriter(reviewWriter,pageable);
        model.addAttribute("reviewList", reviewList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage =((startPage + PagingConst.BLOCK_LIMIT-1)< reviewList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT -1 : reviewList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "reviewPages/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        ReviewDTO reviewDTO = reviewService.findById(id);
        model.addAttribute("review", reviewDTO);
        return "reviewPages/detail";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ReviewDTO reviewDTO){
       reviewService.update(reviewDTO);
       return "redirect:/review/" + reviewDTO.getId();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
