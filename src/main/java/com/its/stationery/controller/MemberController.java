package com.its.stationery.controller;

import com.its.stationery.config.WebConfig;
import com.its.stationery.dto.MemberDTO;
import com.its.stationery.service.CartService;
import com.its.stationery.service.MemberService;
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

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    private final CartService cartService;
    private final WishService wishService;
    @GetMapping("/save-form")
    public String saveForm(){
        return "memberPages/save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        Long id = memberService.save(memberDTO);
        if(id >0){
            cartService.save(memberDTO.getMemberId());
            wishService.save(memberDTO.getMemberId());
            return "redirect:/member/login-form";
        }
        else{
            return "saveFall";
        }
    }
    @PostMapping("/dup-check")
    public @ResponseBody String dupCheck(@RequestParam("memberId") String memberId){
        String checkResult = memberService.dupCheck(memberId);
        return checkResult;
    }

    @GetMapping("/login-form")
    public String loginForm(){
        return "memberPages/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginId", loginResult.getId());
            session.setAttribute("loginMemberId", loginResult.getMemberId());
            session.setAttribute("loginMobile", loginResult.getMemberMobile());
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "redirect:/";

        }
        else {
            return "memberPages/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/myPage/{id}")
    public String myPage(@PathVariable("id") Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberPages/myPage";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberPages/profile";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("updateMember", memberDTO);
        return "memberPages/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        memberService.update(memberDTO);
        session.setAttribute("loginMobile", memberDTO.getMemberMobile());
        session.setAttribute("loginEmail", memberDTO.getMemberEmail());
        return "redirect:/member/profile/" + memberDTO.getId();
    }

    @GetMapping("/withdrawal/{id}")
    public String withdrawalForm(@PathVariable("id") Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberPages/withdrawal";
    }

    @PostMapping("/withdrawal")
    public String withdrawal(HttpSession session){
        memberService.delete((Long) session.getAttribute("loginId"));
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin(Model model, HttpSession session){
        MemberDTO memberDTO = memberService.findByMemberId((String) session.getAttribute("loginMemberId"));
        if("admin".equals(memberDTO.getMemberId())) {
            model.addAttribute("member", memberDTO);
            return "memberPages/admin";
        }
        else{
            return "redirect:/";
        }
    }
    @GetMapping
    public String findAll(@PageableDefault(page = 1) Pageable pageable, Model model,HttpSession session) {
        if("admin".equals(session.getAttribute("loginMemberId"))) {
            Page<MemberDTO> memberList = memberService.paging(pageable);
            model.addAttribute("memberList", memberList);
            int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / WebConfig.PagingConst.BLOCK_LIMIT))) - 1) * WebConfig.PagingConst.BLOCK_LIMIT + 1;
            int endPage = ((startPage + WebConfig.PagingConst.BLOCK_LIMIT - 1) < memberList.getTotalPages()) ? startPage + WebConfig.PagingConst.BLOCK_LIMIT - 1 : memberList.getTotalPages();
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            return "memberPages/list";
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model, HttpSession session){
        if("admin".equals(session.getAttribute("loginMemberId"))) {
            MemberDTO memberDTO = memberService.findById(id);
            model.addAttribute("member", memberDTO);
            return "memberPages/detail";
        }
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
