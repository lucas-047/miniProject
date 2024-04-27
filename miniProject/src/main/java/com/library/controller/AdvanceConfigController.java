package com.library.controller;

import com.library.Config.AdvanceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController

public class AdvanceConfigController {
    @Autowired
    private AdvanceConfigService advanceConfigService;




    @PostMapping("/advance")
    public ModelAndView SettingChange(@RequestParam("price") String price,
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


        return new ModelAndView("redirect:/admin/setting");
    }
}
/*
id 1= penalty price
id 2= user issue book
id 3= faculty issue book
id 4=user due date duration
id 5=faculty due date duration
*/