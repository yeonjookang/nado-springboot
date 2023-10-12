package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String showHello(Model model){
        model.addAttribute("username","연주");
        return "greetings";
    }

    @GetMapping("/bye")
    public String showBye(Model model){
        model.addAttribute("username","수진");
        return "goodbye";
    }
}
