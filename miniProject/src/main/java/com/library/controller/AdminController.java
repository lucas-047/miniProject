package com.library.controller;

import com.library.Config.AdvanceConfigService;
import com.library.dao.AdvanceConfigRepository;
import com.library.dao.PenaltyRepository;
import com.library.dao.TransactionRepository;
import com.library.entities.Penalty;
import com.library.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private PenaltyRepository penaltyRepository;
    @Autowired
    private AdvanceConfigService advanceConfigService;
    @RequestMapping("/index")
    public String dashBoard(Model model, Principal principal){
        model.addAttribute("pageTitle","Library Management System");
        model.addAttribute("userName",principal.getName());
        List<Transaction> list = transactionRepository.findByIssueDate(LocalDate.now());
//        System.out.println(list);
        List<Penalty> list1 = penaltyRepository.findAllByTempIssueDate(LocalDate.now());
//        System.out.println(list1)
        model.addAttribute("Complete_Record",list);
        model.addAttribute("Pending_Record",list1);

        return "Admin/Main";
    }

    @RequestMapping("/books")
    public String Books(Model model,Principal principal){
        model.addAttribute("pageTitle","Books");
        model.addAttribute("userName",principal.getName());
        return "Admin/BookManagement/Book";
    }

    @RequestMapping("/users")
    public String Users(Model model,Principal principal){
        model.addAttribute("pageTitle","Users");
        model.addAttribute("userName",principal.getName());
        return "Admin/User";
    }

    @RequestMapping("/setting")
    public String setting(Model model,Principal principal){
        model.addAttribute("pageTitle","Setting");
        model.addAttribute("userName",principal.getName());
        int penaltyPrice=advanceConfigService.getPenaltyValue();
        int userIssue=advanceConfigService.getNumberOfIssueBookForUser();
        int facultyIssue=advanceConfigService.getNumberOfIssueBookForFaculty();
        int userDue=advanceConfigService.getUserDueDate();
        int facultyDue=advanceConfigService.getFacultyDueDate();
        model.addAttribute("price", penaltyPrice);
        model.addAttribute("userIssue", userIssue);
        model.addAttribute("facultyIssue", facultyIssue);
        model.addAttribute("userDue", userDue);
        model.addAttribute("facultyDue", facultyDue);


        return "Admin/AdvanceConfig";
    }

}
