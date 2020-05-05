package com.grenciss.projdstr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymleafUIController {
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
}