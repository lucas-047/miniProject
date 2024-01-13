package com.library.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @GetMapping("/library")
    public  String libraryController(Model model) {
        model.addAttribute("title","Library Management System");
        return "Library";
    }
    @GetMapping("/about")
    public String About(Model model){
        model.addAttribute("title","About Us");
        return "about";
    }
}
