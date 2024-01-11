package com.example.miniProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {
    @RequestMapping("/login")
    public String loginController() {
        return "login.html";
    }
}
