package com.its.stationery.controller;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.CartDTO;
import com.its.stationery.dto.CartProductDTO;
import com.its.stationery.dto.WishDTO;
import com.its.stationery.dto.WishProductDTO;
import com.its.stationery.service.WishProductService;
import com.its.stationery.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;
    private final WishProductService wishProductService;
    @GetMapping("/{wishMemberId}")
    public String findByMemberId(@PathVariable("wishMemberId") String wishMemberId, Model model, @PageableDefault(page = 1)Pageable pageable){
        WishDTO findDTO = wishService.findByWishMemberId(wishMemberId);
        model.addAttribute("wish", findDTO);
        Page<WishProductDTO> wishProductList = wishProductService.findByWishMemberId(findDTO.getWishMemberId(),pageable);
        model.addAttribute("wishProductList", wishProductList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage =((startPage + PagingConst.BLOCK_LIMIT-1)< wishProductList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT -1 : wishProductList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "wishPages/list";
    }

    @PostMapping("/save")
    public @ResponseBody int save(@ModelAttribute WishProductDTO wishProductDTO){
        int checkResult = wishProductService.check(wishProductDTO);
        if(checkResult > 0){
            int saveResult = wishProductService.save(wishProductDTO);
            return saveResult;
        }
        else{
            return 0;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        wishProductService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
