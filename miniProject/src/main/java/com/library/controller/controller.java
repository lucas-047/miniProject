package com.library.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @GetMapping("/library")
    public  String libraryController(Model model) {
        return "Library";
    }
    @GetMapping("/about")
    public String About(Model model){
        return "about";
    }
}
