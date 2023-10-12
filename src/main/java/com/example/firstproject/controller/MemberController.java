package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    @GetMapping("/signup")
    public String showSignUp(){
        return "members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberDto memberDto){
        Member member = memberDto.toEntity();
        memberRepository.save(member);
        return "";
    }
}
