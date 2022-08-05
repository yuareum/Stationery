package com.its.stationery.controller;

import com.its.stationery.config.WebConfig;
import com.its.stationery.dto.OrderDTO;
import com.its.stationery.dto.ProductDTO;
import com.its.stationery.service.OrderService;
import com.its.stationery.service.ProductService;
import com.its.stationery.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    private final ReviewService reviewService;
    @GetMapping("/save-form/{productId}")
    public String saveForm(@PathVariable("productId") Long productId, Model model){
        ProductDTO productDTO = productService.findById(productId);
        model.addAttribute("product", productDTO);
        return "orderPages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute OrderDTO orderDTO, HttpSession session){
        Long id = orderService.save(orderDTO);
        return "redirect:/order/" + id;
    }

    @GetMapping("/findByMemberId/{orderMemberId}")
    public String findByMemberId(@PathVariable("orderMemberId") String orderMemberId, Model model){
        List<OrderDTO> orderDTOList = orderService.findByOrderMemberId(orderMemberId);
        model.addAttribute("orderList", orderDTOList);
        return "orderPages/myList";
    }

    @GetMapping
    public String findAll(@PageableDefault(page = 1) Pageable pageable, Model model, HttpSession session){
        if("admin".equals(session.getAttribute("loginMemberId"))){
            Page<OrderDTO> orderList = orderService.paging(pageable);
            model.addAttribute("orderList", orderList);
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / WebConfig.PagingConst.BLOCK_LIMIT))) - 1) * WebConfig.PagingConst.BLOCK_LIMIT + 1;
            int endPage = ((startPage + WebConfig.PagingConst.BLOCK_LIMIT - 1) < orderList.getTotalPages()) ? startPage + WebConfig.PagingConst.BLOCK_LIMIT - 1 : orderList.getTotalPages();
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            return "orderPages/list";
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        OrderDTO orderDTO = orderService.findById(id);
        model.addAttribute("order", orderDTO);
        return "orderPages/detail";
    }
    @PostMapping("/processUpdate/{id}")
    public @ResponseBody OrderDTO processUpdate(@PathVariable("id") Long id){
        OrderDTO orderDTO = orderService.findById(id);
        Long updateResult = orderService.processUpdate(orderDTO);
        if(updateResult>0){
            return orderDTO;
        }
        return null;
    }

    @PostMapping("/check")
    public @ResponseBody int check(@ModelAttribute OrderDTO orderDTO){
        List<OrderDTO> orderDTOList = orderService.check(orderDTO);
        if(orderDTOList != null){
            return 1;
        }
        return 0;
    }

    @GetMapping("/productCountsUpdate/{id}")
    public String productCountsUpdate(@PathVariable("id") Long id){
        OrderDTO orderDTO = orderService.findById(id);
        ProductDTO productDTO = productService.findById(orderDTO.getOrderProductId());
        productService.countsUpdate(productDTO,orderDTO.getOrderCounts());
        return "redirect:/order";
    }
}
