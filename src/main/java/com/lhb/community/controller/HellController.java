package com.lhb.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HellController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
