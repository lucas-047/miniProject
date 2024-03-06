package com.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLOutput;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index")
    public String dashBorad(){
        System.out.println("ghfhe");
        return "Admin/Admin_dashBoard";
    }

}
