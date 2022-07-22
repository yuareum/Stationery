package com.its.stationery.controller;

import com.its.stationery.dto.CartDTO;
import com.its.stationery.dto.CartProductDTO;
import com.its.stationery.service.CartProductService;
import com.its.stationery.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartProductService cartProductService;
    @GetMapping("/{cartMemberId}")
    public String findByMemberId(@PathVariable("cartMemberId") String cartMemberId, Model model){
        CartDTO findDTO = cartService.findByMemberId(cartMemberId);
        model.addAttribute("cart", findDTO);
        List<CartProductDTO> cartProductDTOList = cartProductService.findAll(findDTO.getCartMemberId());
        model.addAttribute("cartProductList", cartProductDTOList);
        return "cartPages/list";
    }
}
