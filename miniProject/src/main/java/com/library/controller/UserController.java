package com.library.controller;

import com.library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private com.library.dao.UserRepository userRepository;


    @RequestMapping("/index")
    public String dashBorad(){
        return "User/User_dashBoard";
    }


    @RequestMapping("/profileUpdate")
    public String userprofile(Model model) {
        model.addAttribute("user",new User());
        return "User/profileForm";
    }


    @RequestMapping("/param")
    public String processProfile(@ModelAttribute("user") User user, Model model) {
        System.out.println(user);
        userRepository.save(user);

        return "Public/Success";
    }
}
