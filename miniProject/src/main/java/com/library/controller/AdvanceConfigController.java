package com.library.controller;

import com.library.Config.AdvanceConfigService;
import com.library.dao.AdvanceConfigRepository;
import com.library.entities.AdvanceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class AdvanceConfigController {
    @Autowired
    private AdvanceConfigService advanceConfigService;
    @Autowired
    private AdvanceConfigRepository advanceConfigRepository;


    @PostMapping("/advance")
    public ResponseEntity<?> SettingChange( @RequestParam("price") String price,
                                           @RequestParam("usertotal") String userTotalIssue,
                                           @RequestParam("facultytotal") String facultyTotalIssue,
                                           @RequestParam("userdue") String userduedate,
                                           @RequestParam("facultydue") String facultyduedate, Model model)
    {
        if(price!=null)
        {   advanceConfigService.setPenaltyValue(price);

        }
        if(userTotalIssue!=null)
        {   advanceConfigService.setNumberOfIssueBookForUser(userTotalIssue);

        }
        if(facultyTotalIssue!=null)
        {
            advanceConfigService.setNumberOfIssueBookForFaculty(facultyTotalIssue);
        }
        if(userduedate!=null)
        {
            advanceConfigService.setUserDueDate(userduedate);
        }
        if(facultyduedate!=null)
        {
            advanceConfigService.setFacultyDueDate(facultyduedate);
        }
        String penaltyPrice=advanceConfigRepository.findvalue(1);
        String userIssue=advanceConfigRepository.findvalue(2);
        String facultyIssue=advanceConfigRepository.findvalue(3);
        String userDue=advanceConfigRepository.findvalue(4);
        String facultyDue=advanceConfigRepository.findvalue(5);
        model.addAttribute("price", penaltyPrice);
        model.addAttribute("userIssue", userIssue);
        model.addAttribute("facultyIssue", facultyIssue);
        model.addAttribute("userDue", userDue);
        model.addAttribute("facultyDue", facultyDue);



        return ResponseEntity.ok(model);
    }
}
/*
id 1= penalty price
id 2= user issue book
id 3= faculty issue book
id 4=user due date duration
id 5=faculty due date duration
*/