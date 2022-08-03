package com.its.stationery.controller;

import com.its.stationery.dto.CartDTO;
import com.its.stationery.dto.WishDTO;
import com.its.stationery.dto.WishProductDTO;
import com.its.stationery.service.WishProductService;
import com.its.stationery.service.WishService;
import lombok.RequiredArgsConstructor;
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
    public String findByMemberId(@PathVariable("wishMemberId") String wishMemberId, Model model){
        WishDTO findDTO = wishService.findByWishMemberId(wishMemberId);
        model.addAttribute("wish", findDTO);
        List<WishProductDTO> wishProductDTOList = wishProductService.findByWishMemberId(wishMemberId);
        model.addAttribute("wishProductList", wishProductDTOList);
        return "/wishPages/list";
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
