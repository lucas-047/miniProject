package com.library.controller;

import com.library.Config.PenaltyService;
import com.library.dao.PenaltyRepository;
import com.library.dao.RequestmanagementRepository;
import com.library.dao.UserRepository;
import com.library.entities.Requestmanagement;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReissueHandlingController {
    @Autowired
    private RequestmanagementRepository requestmanagementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
   private PenaltyRepository penaltyRepository;
    @Autowired
    private PenaltyService penaltyService;
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
    {  // String name="vasu";
        String name=principal.getName();
        System.out.println("principal is "+name);
        boolean check=requestmanagementRepository.existsByBookId(bookId);
        //Requestmanagement rq=requestmanagementRepository.findByBookId(bookId);
        int penaltyStatus=userRepository.findpenaltybyusername(name);
        if(penaltyStatus==1)
        {
            model.addAttribute("penalty", true);
            return "Public/timepass";
        }
        else {
            int count=0;

            if (check) {
            Requestmanagement requestmanagement=requestmanagementRepository.findByBookId(bookId);
             count =requestmanagement.getCount();
            if(count>=3)
            {
                model.addAttribute("maxCount", true);
                System.out.println("maximum renewed");
                 return "Public/timepass";
            }

            }

//                int count = rq.getCount();
//
//                if (count == 3) {
//
//                }
//                count++;
//                rq.setCount(count);
                LocalDate currentDate = LocalDate.now();
                Requestmanagement obj = new Requestmanagement();
                obj.setBookId(bookId);
                obj.setStatus(Requestmanagement.Status.PENDING);
                obj.setRequestDate(currentDate);
                obj.setCount(count);
                obj.setIssueDate(penaltyRepository.findissuedate(bookId));
                obj.setUserId(name);

                requestmanagementRepository.saveAndFlush(obj);






        }
        return "Public/timepass";
    }
    //        fetch here bookid and userid
    @PostMapping("/admin/approverequest")
    public ResponseEntity<Map<String, Object>> approveRequest(@RequestParam("requestId") String id,Model model) {

      // String bookId=;
        System.out.println("hoo hooo hoooooo");
        System.out.println("id is "+id);
      //  String id="1";
//        Double id= Double.valueOf(idd);
        // Perform the necessary logic to update the status of the request with the given ID
        Requestmanagement rq1=requestmanagementRepository.findByRequestId(Double.valueOf(id));

        penaltyService.issue(rq1.getBookId(), rq1.getUserId(),rq1.getCount()+1);
        rq1.setStatus(Requestmanagement.Status.ACCEPT);
        LocalDate approveDate=LocalDate.now();
        rq1.setApproveDate(approveDate);
        rq1.setCount(rq1.getCount()+1);
        System.out.println("send details");
        requestmanagementRepository.saveAndFlush(rq1);
       // model.addAttribute("st",rq1.getStatus());


        Map<String, Object> response = new HashMap<>();
      //  boolean success = approveRequestById(requestId); // Implement this method

            response.put("success", true);
            response.put("message", "Request approved successfully");
        response.put("approveDate", approveDate);
        response.put("reNew",rq1.getCount());

        return ResponseEntity.ok(response);
    }
    @PostMapping("/admin/rejectrequest")
    public ResponseEntity<Map<String, Object>> rejectRequest(@RequestParam("requestId") String id,Model model)
    {
        Map<String, Object> response = new HashMap<>();
        //  boolean success = approveRequestById(requestId); // Implement this method
        Requestmanagement rq1=requestmanagementRepository.findByRequestId(Double.valueOf(id));
        rq1.setStatus(Requestmanagement.Status.REJECT);
        LocalDate approveDate=LocalDate.now();
        rq1.setApproveDate(approveDate);
        requestmanagementRepository.saveAndFlush(rq1);
        response.put("success", true);
        response.put("approveDate", approveDate);
       return ResponseEntity.ok(response);

    }
}
