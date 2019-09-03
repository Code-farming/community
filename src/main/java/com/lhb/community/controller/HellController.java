package com.lhb.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HellController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "username") String username, Model model) {
        model.addAttribute("username",username);
        return "index";
    }
}
