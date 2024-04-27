package com.library.controller;

import com.library.dao.PenaltyRepository;
import com.library.dao.RequestmanagementRepository;
import com.library.dao.UserRepository;
import com.library.entities.Requestmanagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ReissueHandlingController {
    @Autowired
    private RequestmanagementRepository requestmanagementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
   private PenaltyRepository penaltyRepository;
    @RequestMapping("/user/requestnew")
    public ModelAndView getrequestpage()
    {   ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("User/RequestReissue");
        return modelAndView;
    }
    @RequestMapping("/admin/requestissue")
    public ModelAndView getstatuspage(Model model)
    {   ModelAndView modelAndView = new ModelAndView();
        List<Requestmanagement> rq=requestmanagementRepository.findAll();
        model.addAttribute("rq",rq);
        modelAndView.setViewName("Admin/RequestStatusCheck");
        return modelAndView;
    }
    @PostMapping("/user/requestbook")
    public String createrequest(@RequestParam("bookId") int bookId, Model model, Principal principal)
    {   String name="vasu";
        //String name=principal.getName();
        boolean check=requestmanagementRepository.existsByBookId(bookId);
        //Requestmanagement rq=requestmanagementRepository.findByBookId(bookId);
        int penaltyStatus=userRepository.findpenaltybyusername(name);
        if(penaltyStatus==1)
        {
            model.addAttribute("penalty", true);
            return "Public/timepass";
        }
        else {


            if (check) {
            Requestmanagement requestmanagement=requestmanagementRepository.findByBookId(bookId);
            int count =requestmanagement.getCount();
            if(count==3)
            {
                model.addAttribute("maxCount", true);
                System.out.println("maximum renewed");
                 return "Public/timepass";
            }

            } else {

//                int count = rq.getCount();
//
//                if (count == 3) {
//
//                }
//                count++;
//                rq.setCount(count);
            }

            LocalDate currentDate = LocalDate.now();
            Requestmanagement obj = new Requestmanagement();
            obj.setBookId(bookId);
            obj.setStatus("pending");
            obj.setRequestDate(currentDate);
            obj.setIssueDate(penaltyRepository.findissuedate(bookId));
            obj.setUserId(1);

            requestmanagementRepository.saveAndFlush(obj);


        }
        return "Public/timepass";
    }
    @GetMapping("/admin/approverequest")
    public ResponseEntity<String> approveRequest(Model model) {
        System.out.println("hoo hooo hoooooo");
        String id="1";
//        Double id= Double.valueOf(idd);
        // Perform the necessary logic to update the status of the request with the given ID
        Requestmanagement rq1=requestmanagementRepository.findByRequestId(Double.valueOf(id));
        rq1.setStatus("Approved");
        requestmanagementRepository.saveAndFlush(rq1);
       // model.addAttribute("st",rq1.getStatus());


        return ResponseEntity.ok("OK");
    }
}
