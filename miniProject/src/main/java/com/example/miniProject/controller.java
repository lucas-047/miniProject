package com.example.miniProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @GetMapping("/library")
    public  String libraryController(){return "Library";}
}
