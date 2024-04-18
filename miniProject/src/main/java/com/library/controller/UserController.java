package com.library.controller;

import com.library.dao.PenaltyRepository;
import com.library.dao.UserRepository;
import com.library.entities.Penalty;
import com.library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PenaltyRepository penaltyRepository;

    @RequestMapping("/searchBook")
    public String bookSearch() {
        return "User/bookSearch";
    }

    @RequestMapping("/index")
    public String dashBoard(Model model, Principal principal) {
        List<Penalty> list = penaltyRepository.findAllByTempUserId(principal.getName());
        System.out.println(list);
        model.addAttribute("Record",list);
        return "User/Main";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("user", new User());
        return "Public/Upload";
    }

    @PostMapping("/upload")
    public String processProfile(@ModelAttribute("user") User user, Model model) {
        System.out.println(user);
        userRepository.save(user);
        return "Public/Success";
    }
}