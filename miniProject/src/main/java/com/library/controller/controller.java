package com.library.controller;

import com.library.dao.RegRepository;
import com.library.entities.RegData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class controller {
    @Autowired
    private RegRepository regRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/library")
    public  String libraryController(Model model) {
        model.addAttribute("title","Library Management System");
        return "Public/Home";
    }


    @GetMapping("/About")
    public  String AboutController(Model model) {
        model.addAttribute("title","About");
        return "Public/About";
    }


    @GetMapping("/signIn")
    public  String LoginController(Model model) {
        model.addAttribute("title","Login");
        return "Public/Login";
    }

    @GetMapping("/password_reset")
    public String forgotPassword(){
        return "Public/forgotPassword";
    }


    @GetMapping("/Registration")
    public  String RegController(Model model) {
        model.addAttribute("title","Registration");
        model.addAttribute("regData",new RegData());
        return "Public/SignUp";
    }


    @PostMapping("/processRegistration")
    public String processRegistration(@Valid @ModelAttribute("regData") RegData regData, BindingResult result) {

        if (result.hasErrors()) {
            return "Public/SignUp";
        } else {
            regData.setPassword(passwordEncoder.encode(regData.getPassword()));
            regData.setRole(("ROLE_" + regData.getRole()).toUpperCase());
            regRepository.save(regData);
            return "Public/Success";
        }
    }


    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/index";
        }
        return "redirect:/user/index";
    }
}
