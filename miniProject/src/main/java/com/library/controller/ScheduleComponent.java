package com.library.controller;

import com.library.Config.EmailService;
import com.library.Config.PenaltyService;
import com.library.dao.PenaltyRepository;
import com.library.dao.RequestmanagementRepository;
import com.library.entities.Penalty;
import com.library.entities.Requestmanagement;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Component

public class ScheduleComponent {
    @Autowired
    private EmailService emailService;
    @Autowired
    private PenaltyRepository penaltyRepository;
    @Autowired
    private PenaltyService penaltyService;
    @Autowired
    private RequestmanagementRepository requestmanagementRepository;


//  @Scheduled(cron =" 0 */1 * * * *")
    public void notification()
    {      List<Penalty> penaltyList= penaltyRepository.findAll();
        for ( Penalty forcheck:penaltyList)
        {
                LocalDate checkdue= forcheck.getTempDueDate();
                LocalDate checkreturn=LocalDate.now();
            int day= (int) ChronoUnit.DAYS.between(checkdue,checkreturn);


            if(day==0)
            {
                    System.out.println("bookid= "+forcheck.getTempBookId()+" today is duedate");


            }
            else if(day==-1)
            {
                System.out.println("bookid= "+forcheck.getTempBookId()+" 1 day remaining");
            }
            else if(day>0) {
                System.out.println("day is "+day);
                day= Math.abs(day);
               penaltyService.savePentaltyStatus(forcheck.getTempBookId(),day);
                System.out.println("bookid= "+forcheck.getTempBookId()+"  ops you got penalty "+forcheck.getTempPenaltyStatus());
            }

        }

        String subject="schedule msg";
        String message="penalty bharne bhai";
        String email="bronylast46@gmail.com";
        System.out.println("schedule processing");
//        boolean flag = this.emailService.sendemail(subject, message, email);
//        if (flag) {
//           System.out.println("schedule email send ");
//
//        } else {
//            System.out.println("schedule email not send ");
//        }
    }
}
