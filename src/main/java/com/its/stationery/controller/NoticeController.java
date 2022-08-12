package com.its.stationery.controller;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.NoticeDTO;
import com.its.stationery.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping
    public String findAll(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<NoticeDTO> noticeList = noticeService.paging(pageable);
        model.addAttribute("noticeList", noticeList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < noticeList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : noticeList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "noticePages/list";
    }

    @GetMapping("/save-form")
    public String saveForm(){
        return "/noticePages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute NoticeDTO noticeDTO) throws IOException {
        Long id = noticeService.save(noticeDTO);
        return "redirect:/notice/" + id;
    }

}
