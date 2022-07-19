package com.its.stationery.controller;

import com.its.stationery.dto.MemberDTO;
import com.its.stationery.service.MemberService;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/save-form")
    public String saveForm(){
        return "memberPages/save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        memberService.save(memberDTO);
        return "redirect:/member/login-form";
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

}
