package com.library.controller;

import com.library.dao.RegRepository;
import com.library.entities.RegData;
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
        return "Home";
    }
    @GetMapping("/About")
    public  String AboutController(Model model) {
        model.addAttribute("title","About");
        return "About";
    }
    @GetMapping("/Login")
    public  String LoginController(Model model) {
        model.addAttribute("title","Login");
        return "Login";
    }
    @GetMapping("/Registration")
    public  String RegController(Model model) {
        model.addAttribute("title","Registration");
        model.addAttribute("regData",new RegData());
        return "SignUp";
    }
    @PostMapping("/process")
    public String processLogin(@Valid @ModelAttribute("regData") RegData regData, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result);
            System.out.println(regData);
            return "SignUp";
        } else {
            regData.setPassword(passwordEncoder.encode(regData.getPassword()));
            regData.setRole("ROLE_" + regData.getRole());
            regRepository.save(regData);
            return "Success";
        }
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test(){

        return "working";
    }
}
