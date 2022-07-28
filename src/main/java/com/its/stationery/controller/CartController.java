package com.its.stationery.controller;

import com.its.stationery.dto.CartDTO;
import com.its.stationery.dto.CartProductDTO;
import com.its.stationery.service.CartProductService;
import com.its.stationery.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartProductService cartProductService;

    @GetMapping("/{id}")
    public String findByMemberId(@PathVariable("id") Long id, Model model){
        CartDTO findDTO = cartService.findById(id);
        model.addAttribute("cart", findDTO);
        List<CartProductDTO> cartProductDTOList = cartProductService.findByCartMemberId(findDTO.getCartMemberId());
        model.addAttribute("cartProductList", cartProductDTOList);
        return "cartPages/list";
    }

    @PostMapping("/save")
    public @ResponseBody int save(@ModelAttribute CartProductDTO cartProductDTO){
        int checkResult = cartProductService.check(cartProductDTO);
        if(checkResult>0){
            int saveResult = cartProductService.save(cartProductDTO);
            return saveResult;
        }
        return 0;
    }
}
