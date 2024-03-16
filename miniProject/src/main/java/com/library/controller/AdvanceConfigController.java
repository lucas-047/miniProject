package com.library.controller;

import com.library.Config.AdvanceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class AdvanceConfigController {
    @Autowired
    private AdvanceConfigService advanceConfigService;
    @RequestMapping("/setting")
    public String gotoSettingPage()
    {
        return "Public/AdvanceConfig";
    }
    @PostMapping("/advance")
    public String SettingChange(@RequestParam("price") String price,
                                @RequestParam("usertotal") String userTotalIssue,
                                @RequestParam("facultytotal") String facultyTotalIssue,
                                @RequestParam("userdue") String userduedate,
                                @RequestParam("facultydue") String facultyduedate)
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




        return "Public/timepass";
    }
}
