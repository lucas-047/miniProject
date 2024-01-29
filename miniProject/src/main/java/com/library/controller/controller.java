package com.library.controller;

import com.library.Config.EmailService;
import com.library.dao.RegRepository;
import com.library.entities.RegData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@Controller
public class controller {
    @Autowired
    private EmailService emailService;
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


    @GetMapping("/Registration")
    public  String RegController(Model model) {
        model.addAttribute("title","Registration");
        model.addAttribute("regData",new RegData());
        return "Public/SignUp";
    }


    @PostMapping("/processRegistration")
    public String processRegistration(@RequestParam("email") String email,@Valid @ModelAttribute("regData") RegData regData, BindingResult result, HttpSession session,Model model) {
        Random rand = new Random();
        int max=99999,min=10000;
        System.out.println("Generated numbers are within "+min+" to "+max);
        int otp=rand.nextInt(max - min + 1) + min;
        System.out.println(otp);
//        String numbers = "0123456789";
//        Random rndm_method = new Random();
//        char[] otp1 = new char[5];
//        for (int i = 0; i < 5; i++) {
//            otp1[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
//        }
        if(result.hasErrors())
        {
            return "Public/SignUp";
        }
        else {
//            String change=new String(otp1);


            System.out.println(otp);
            String subject="OTP for Email verify";
            String message="<h1>OTP="+otp+"</h1>";
            String to=email;
            boolean flag= this.emailService.sendemail(subject,message,to);
            if(flag)
            {   session.setAttribute("regData",regData);
                model.addAttribute("data",regData);
                session.setAttribute("myotp",otp);
                RegData reg=(RegData) model.getAttribute("data");
                System.out.println(reg);
                System.out.println(model.getAttribute("data"));
                //session.setAttribute("myemail",email);
                return "public/otp";

            }
            else {
                session.setAttribute("message","check your email id");
                return "public/SignUp";
            }
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