package com.its.stationery.controller;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.service.ProductService;
import com.its.stationery.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final WishService wishService;

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
    private String category(@RequestParam("categoryId") Long categoryId, Model model){
        if(categoryId == 1){
            List<ProductDTO> productDTOList = productService.categoryList(categoryId);
            model.addAttribute("productList", productDTOList);
            return "prdouctPages/categoryList";
        }
        return "redirect:/product";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        ProductDTO productDTO = productService.detail(id);
        model.addAttribute("product", productDTO);
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
    public String delete(@PathVariable("id") Long id){
        productService.delete(id);
        return "redirect:/product";
    }
    @GetMapping("/search")
    public String search(@RequestParam("q") String q, Model model){
        List<ProductDTO> searchList = productService.search(q);
        model.addAttribute("searchList", searchList);
        return "productPages/searchList";
    }
}
