package com.library.controller;

import com.library.entities.userProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private com.library.dao.userProfileRepository userProfileRepository;


    @RequestMapping("/index")
    public String dashBorad(){
        return "User/User_dashBoard";
    }


    @RequestMapping("/profileUpdate")
    public String userprofile(Model model) {
        model.addAttribute("user",new userProfile());
        return "User/profileForm";
    }


    @RequestMapping("/processProfileReg")
    public String processProfile(@ModelAttribute("user") userProfile user, Model model) {
        userProfileRepository.save(user);
        return "Public/Success";
    }
}
