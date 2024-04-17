package com.library.controller;

import com.library.dao.PenaltyRepository;
import com.library.dao.TransactionRepository;
import com.library.entities.Penalty;
import com.library.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PenaltyRepository penaltyRepository;
    @RequestMapping("/index")
    public String dashBoard(Model model){
        model.addAttribute("pageTitle","Library Management System");
        List<Transaction> list = transactionRepository.findByIssueDate(LocalDate.now());
        System.out.println(list);
        List<Penalty> list1 = penaltyRepository.findAllByTempIssueDate(LocalDate.now());
        System.out.println(list1);
        model.addAttribute("Record",list1);
        return "Admin/Main";
    }

    @RequestMapping("/books")
    public String Books(Model model){
        model.addAttribute("pageTitle","Books");
        return "Admin/BookManagement/Book";
    }

    @RequestMapping("/users")
    public String Users(Model model){
        model.addAttribute("pageTitle","Users");
        return "Admin/User";
    }

    @RequestMapping("/setting")
    public String setting(Model model){
        model.addAttribute("pageTitle","Setting");
        System.out.println("setting");
        return "Admin/AdvanceConfig";
    }

}
