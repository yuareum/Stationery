package com.its.stationery.controller;

import com.its.stationery.common.PagingConst;
import com.its.stationery.config.WebConfig;
import com.its.stationery.dto.*;
import com.its.stationery.entity.InquiryEntity;
import com.its.stationery.service.*;
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

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final WishProductService wishProductService;

    private final ReviewService reviewService;
    private final InquiryService inquiryService;


    @GetMapping("/save-form")
    public String saveForm(){
        return "productPages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ProductDTO productDTO) throws IOException {
        Long id = productService.save(productDTO);
        return "redirect:/product/"+id;
    }
    @GetMapping
    public String findAll(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<ProductDTO> productList = productService.paging(pageable);
        model.addAttribute("productList", productList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < productList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : productList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "productPages/list";
    }

    @GetMapping("/category")
    private String category(@RequestParam("categoryName") Long categoryName, Model model){
        List<ProductDTO> productDTOList = productService.categoryList(categoryName);
        model.addAttribute("productList", productDTOList);
        return "prdouctPages/categoryList";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model,HttpSession session){
        ProductDTO productDTO = productService.detail(id);
        model.addAttribute("product", productDTO);
        List<ReviewDTO> reviewDTOList = reviewService.findByReviewProductId(id);
        model.addAttribute("reviewList", reviewDTOList);
        WishProductDTO wishProductDTO = wishProductService.findByWishMemberIdAndWishProductName((String) session.getAttribute("loginMemberId"), productDTO.getProductName());
        model.addAttribute("wishProduct", wishProductDTO);
        List<InquiryDTO> inquiryDTOList = inquiryService.findByInquiryProductId(id);
        model.addAttribute("inquiryList", inquiryDTOList);
        return "productPages/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        ProductDTO productDTO = productService.findById(id);
        model.addAttribute("product", productDTO);
        return "productPages/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute ProductDTO productDTO){
        productService.update(productDTO);
        return "redirect:/product/" + productDTO.getId();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public String search(@RequestParam("q") String q, @PageableDefault(page = 1) Pageable pageable, Model model){
        Page<ProductDTO> searchList = productService.search(q, pageable);
        model.addAttribute("searchList", searchList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage =((startPage + PagingConst.BLOCK_LIMIT-1)< searchList.getTotalPages())?startPage + PagingConst.BLOCK_LIMIT -1 : searchList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("q", q);
        return "productPages/searchList";
    }

}
