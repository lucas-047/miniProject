package com.library.controller;
import com.library.dao.userProfileRepository;
import com.library.dao.RegRepository;
import com.library.entities.RegData;
import com.library.entities.userProfile;
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
    private userProfileRepository userProfileRepoOb;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @GetMapping("/library")
    public  String libraryController(Model model) {
        model.addAttribute("title","Library Management System");
        return "Home";
    }
    @PostMapping("/Aboutpage")
    public  String AboutController(Model model) {
        model.addAttribute("title","About");
        return "About";
    }
    @GetMapping("/Login")
    public  String LoginController(Model model) {
        model.addAttribute("title","Login");
        return "Login";
    }

    @GetMapping("/password_reset")
    public String forgotPassword(){
        return "Public/forgotPassword";
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
    @RequestMapping("/profile")
    public String userprofile()
    {   System.out.println("hrhrh");
        return "profile.html";
    }
    @RequestMapping(path="/param",method= RequestMethod.POST)
    public String proccessProfile(@ModelAttribute userProfile user,Model model)
    {           System.out.println(user);



        return "success";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){

        return "working";
    }
}
