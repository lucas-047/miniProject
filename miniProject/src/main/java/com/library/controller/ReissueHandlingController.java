package com.library.controller;

import com.library.dao.PenaltyRepository;
import com.library.dao.RequestmanagementRepository;
import com.library.entities.Requestmanagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class ReissueHandlingController {
    @Autowired
    private RequestmanagementRepository requestmanagementRepository;
    @Autowired
   private PenaltyRepository penaltyRepository;
    @RequestMapping("/requestnew")
    public ModelAndView getrequestpage()
    {   ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Public/RequestReissue");
        return modelAndView;
    }
    @RequestMapping("/requestissue")
    public ModelAndView getstatuspage(Model model)
    {   ModelAndView modelAndView = new ModelAndView();
        List<Requestmanagement> rq=requestmanagementRepository.findAll();
        model.addAttribute("rq",rq);
        modelAndView.setViewName("Public/RequestStatusCheck");
        return modelAndView;
    }
    @PostMapping("/requestbook")
    public String createrequest(@RequestParam("bookId") int bookId, Model model)
    {
        Requestmanagement rq=requestmanagementRepository.findByBookId(bookId);

        if(rq==null) {




        }
        else {

            int count= rq.getCount();

            if(count==3)
            {   model.addAttribute("maxCount",true);
                return "Public/timepass";
            }
            count++;
            rq.setCount(count);
        }

            LocalDate currentDate=LocalDate.now();
            Requestmanagement obj=new Requestmanagement();
            obj.setBookId(bookId);
            obj.setStatus("pending");
            obj.setRequestDate(currentDate);
            obj.setIssueDate(penaltyRepository.findissuedate(bookId));
            obj.setUserId(1);
            requestmanagementRepository.saveAndFlush(obj);



        return "Public/timepass";
    }
    @PostMapping("/approverequest")
    public ResponseEntity<String> approveRequest(@RequestBody String id,Model model) {
        System.out.println("hoo hooo hoooooo");
//        Double id= Double.valueOf(idd);
        // Perform the necessary logic to update the status of the request with the given ID
        Requestmanagement rq1=requestmanagementRepository.findByRequestId(Double.valueOf(id));
        rq1.setStatus("Approved");
        requestmanagementRepository.saveAndFlush(rq1);
       // model.addAttribute("st",rq1.getStatus());


        return ResponseEntity.ok("OK");
    }
}
