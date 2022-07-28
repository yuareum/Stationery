package com.its.stationery.controller;

import com.its.stationery.dto.CartDTO;
import com.its.stationery.dto.CartProductDTO;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.service.CartProductService;
import com.its.stationery.service.CartService;
import com.its.stationery.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final ProductService productService;
    @GetMapping("/{cartMemberId}")
    public String findByCartMemberId(@PathVariable("cartMemberId") String cartMemberId, Model model){
        CartDTO findDTO = cartService.findByCartMemberId(cartMemberId);
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

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        cartProductService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
