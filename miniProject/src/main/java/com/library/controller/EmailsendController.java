package com.library.controller;


import com.library.Config.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class EmailsendController {
    @Autowired
    private EmailService emailService;
    @RequestMapping("/")
    public String forgotpage()
    {   System.out.println("forgot page method is used");

        return "forgotPassword";
    }
    @PostMapping("/nl")
    public String emailfatching(@RequestParam("username") String email, HttpSession session)
    {   System.out.println(email);
//        Random random= new Random(1000);
//        int otp=random.nextInt(999999);
//        System.out.println(otp);
        //int length=5;
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp1 = new char[5];
        for (int i = 0; i < 5; i++) {
            otp1[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        String change=new String(otp1);

        int otp=Integer.parseInt(change);
        System.out.println(otp);
        String subject="OTP for VRaj lodu";
        String message="<h1>OTP="+otp+"</h1>";
        String to=email;
        boolean flag= this.emailService.sendemail(subject,message,to);
        if(flag)
        {
            session.setAttribute("myotp",otp);
            session.setAttribute("myemail",email);
//            mail send thai gyo and otp vadu page open krvanu
            return "verify-otp.jsp";

        }
        else {
            session.setAttribute("message","check your email id");
//            mail send nai thayo atle invalid email
            return "change_pass.jsp";
        }

    }
//    verify-otp page for verify otp
    @PostMapping("/verify-otp")
    public String veryfive(@RequestParam("otp") Integer otp,HttpSession session)
    {
        int myotp=(Integer)session.getAttribute("myotp");
        String email=(String)session.getAttribute("myemail");
        if(myotp==otp)
       {
//           otp verified and go to password change page
            session.setAttribute("msg","otp verified");
        }
        else
        {
//            farithi otp vadu page with otp is wrong
            session.setAttribute("msg","otp not verified");
        }
        return "status.jsp";
    }
}
