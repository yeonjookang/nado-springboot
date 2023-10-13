package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberDto;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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
        Member saved = memberRepository.save(member);
        return "redirect:/members/"+saved.getId();
    }

    @GetMapping("/members/{memberId}")
    public String showMember(@PathVariable Long memberId, Model model){
        Member findMember = memberRepository.findById(memberId).orElse(null);
        model.addAttribute("member",findMember);
        return "members/show";
    }

    @GetMapping("/members")
    public String showMembers(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        return "members/index";
    }
}
