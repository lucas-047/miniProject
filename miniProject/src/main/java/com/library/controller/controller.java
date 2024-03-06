package com.library.controller;

import com.library.Config.EmailService;
import com.library.dao.UserRepository;
import com.library.entities.User;
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
    private UserRepository userRepository;

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
        model.addAttribute("regData",new User());
        return "Public/SignUp";
    }


    @PostMapping("/processRegistration")
    public String processRegistration(@RequestParam("email") String email, @Valid @ModelAttribute("regData") User user, BindingResult result, HttpSession session, Model model) {
        Random rand = new Random();
        int max=99999,min=10000;
        System.out.println("Generated numbers are within "+min+" to "+max);
        int otp=rand.nextInt(max - min + 1) + min;
        System.out.println(otp);
//
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
            {   session.setAttribute("regData", user);
                model.addAttribute("data", user);
                session.setAttribute("myotp",otp);

                User reg=(User) model.getAttribute("data");
                System.out.println(reg);
                System.out.println(model.getAttribute("data"));
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