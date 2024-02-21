package com.library.controller;

import com.library.Config.EmailService;
import com.library.Config.PenaltyService;
import com.library.dao.PenaltyRepository;
import com.library.entities.Penalty;
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


    @Scheduled(cron =" 0 */1 * * * *")
    public void notification()
    {      List<Penalty> penaltyList= penaltyRepository.findAll();
        for ( Penalty forcheck:penaltyList)
        {
                LocalDate checkdue= forcheck.getTempDueDate();
                LocalDate checkreturn=forcheck.getTempReturnDate();
            int day= (int) ChronoUnit.DAYS.between(checkreturn,checkdue);
            if(day==0)
            {
                System.out.println("on time");

            }
            else if(day>0)
            {
                System.out.println("you are early bird");
            }
            else {
               penaltyService.savePentaltyStatus(forcheck.getTempBookId(),forcheck.getTempPenaltyStatus());
                System.out.println("ops you got penalty");
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
