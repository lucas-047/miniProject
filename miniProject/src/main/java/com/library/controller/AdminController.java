package com.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index")
    public String dashBorad(Model model){
        model.addAttribute("pageTitle","Library Management System");
        return "Admin/Main";
    }

    @RequestMapping("/books")
    public String Books(Model model){
        model.addAttribute("pageTitle","Books");
        return "Admin/Book";
    }
    @RequestMapping("/users")
    public String Users(Model model){
        model.addAttribute("pageTitle","Users");
        return "Admin/User";
    }

    @RequestMapping("/setting")
    public String setting(Model model){
        model.addAttribute("pageTitle","Setting");
        return "Admin/AdvanceConfig";
    }

}
