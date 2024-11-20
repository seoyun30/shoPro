package com.example.shopro.controller;

import com.example.shopro.dto.MemberDTO;
import com.example.shopro.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @GetMapping("/new")
    public String memberForm(Model model){

        model.addAttribute("memberDTO", new MemberDTO());

        return "member/memberForm";
    }
    @PostMapping("/new")
    public String memberFormPost(@Valid MemberDTO memberDTO, BindingResult bindingResult,Model model){
        //입력을 받을 때 유효성검사를 할것이며, vailde?
        log.info("저장의 POST로 들어온 memberDTO :"+memberDTO);
        if(bindingResult.hasErrors()){
            //유효성 검사 에러시 회원가입 페이지로 이동
            //에러내용을 가지고 자동진행
            //단 return으로 redirect 안됨 그건 RdirectAttributes에 따로 담아야함
            //에러는 로그
            log.info(bindingResult.getAllErrors());
            //이 에러를 가져오는 getAllErrors의 내용을 리다이렉트로 보낼때 가져가면된다.

            return "member/memberForm";
        }
        //유효성검사에 이상이 없다면 저장
        try {
            memberService.saverMember(memberDTO);
        }catch (IllegalStateException e){

            model.addAttribute("msg", e.getMessage());

            return "member/memberForm";
        }

        //저장 후 특정페이지로 이동하게 만들것
    return null;
    }

    @GetMapping("/login")
    public String loginMember(){
        return "/member/loginForm";
    }





}
