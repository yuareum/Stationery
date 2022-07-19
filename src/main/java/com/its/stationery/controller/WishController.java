package com.its.stationery.controller;

import com.its.stationery.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wish")
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;
}
