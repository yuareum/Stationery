package com.its.stationery.controller;

import com.its.stationery.common.PagingConst;
import com.its.stationery.dto.NoticeDTO;
import com.its.stationery.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        NoticeDTO noticeDTO = noticeService.detail(id);
        model.addAttribute("notice", noticeDTO);
        return "noticePages/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("updateNotice", noticeDTO);
        return "noticePages/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute NoticeDTO noticeDTO){
        Long id = noticeService.update(noticeDTO);
        return "redirect:/notice/" + id;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        noticeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
