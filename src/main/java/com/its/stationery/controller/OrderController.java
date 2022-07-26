package com.its.stationery.controller;

import com.its.stationery.dto.OrderDTO;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.service.OrderService;
import com.its.stationery.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    @GetMapping("/save-form/{productId}")
    public String saveForm(@PathVariable("productId") Long productId, Model model){
        ProductDTO productDTO = productService.findById(productId);
        model.addAttribute("product", productDTO);
        return "orderPages/save";
    }

    @PostMapping("/save")
    public @ResponseBody String save(@ModelAttribute OrderDTO orderDTO, HttpSession session){
        orderService.save(orderDTO);
        return "ok";
    }

    @GetMapping("/findByMemberId/{orderMemberId}")
    public String findByOrderMemberId(@PathVariable("orderMemberId") String orderMemberId, Model model){
        List<OrderDTO> orderDTOList = orderService.findByOrderMemberId(orderMemberId);
        model.addAttribute("orderList", orderDTOList);
        return "/orderPages/myList";
    }
}
