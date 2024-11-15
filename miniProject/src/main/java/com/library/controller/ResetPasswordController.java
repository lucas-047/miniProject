package com.library.controller;

import java.lang.Exception;
import com.library.Config.EmailService;
import com.library.cipher.stringCipher;
import com.library.dao.RegRepository;
import com.library.entities.RegData;
import com.library.entities.forgotPassword;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.library.entities.RegData;

@Controller
public class ResetPasswordController {

    HttpSession s;
    @Autowired
    private EmailService emailService;
    private final stringCipher cipher;
    @Autowired
    private RegRepository regRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResetPasswordController() {
        cipher = new stringCipher();
    }

    @RequestMapping("/password_reset")
    public String forgotPassword()
    {
        return "Public/forgotPassword";
    }

    @PostMapping("/sendResetPasswordMail")
    public String fetchEmail(@RequestParam("email") String email, Model model) throws Exception {
        RegData regData = regRepository.findByEmail(email);
        if(regData == null){
            model.addAttribute("error",true);
            return "Public/forgotPassword";
        }else {
            String encoded_email = cipher.encode(email);
            String subject = "Reset password";
            String message = """
                    <html>
                    <head></head>
                    <body>
                    <h1>We found request for resetting password for your Library management System.<h1>
                                        
                    your reset password link :
                                        
                    <a href="http://localhost:8080/resetPassword?id=""" + encoded_email + """
                    ">Click here</a>
                                        
                    Kindly Ignore if not you...
                    </body>
                    </html>
                                        
                    """;

            boolean flag = this.emailService.sendemail(subject, message, email);
            if (flag) {
                return "Public/EmailSuccess";

            } else {
                model.addAttribute("notSend",true);
                return "Public/forgotPassword";
            }
        }
    }
    @RequestMapping("/resetPassword")
    public String resetPassword(@RequestParam(name = "id") String email, Model model,HttpSession session) {
        email = cipher.decode(email);
        session.setAttribute("email",email);
        //session.setMaxInactiveInterval(40);
        long lastAccessedTime = session.getLastAccessedTime();
        //long lastAccessedTIme;
        s.setAttribute("time",lastAccessedTime);

        model.addAttribute("forgotPass",new forgotPassword());


        return "Public/ResetPassSuccess";
    }
    @RequestMapping("/processResetPassword")
    public String processResetPassword(@ModelAttribute(name = "forgotPass") forgotPassword forgotPass,Model model,HttpSession session){
          long currentTime = System.currentTimeMillis();
            long check=(long)s.getAttribute("time");
            if(currentTime-check < 5000 )
            {  // s.equals(session.getAttribute("email"));
                if(forgotPass.getNewPassword().equals(forgotPass.getConfirmPassword())){
                    RegData regData = regRepository.findByEmail((String) session.getAttribute("email"));
                    regData.setPassword(passwordEncoder.encode(forgotPass.getNewPassword()));
                    regRepository.save(regData);
                    return "Public/Success";
                }
                else {
                    model.addAttribute("PassnotMatch",true);
                    return "Public/ResetPassSuccess";
                }
            }
            else {

                return "public/expiry";
            }
    }
    @PostMapping("/validateOtp")
    public String validateOtp(@RequestParam("otp") Integer userOtp,Model model,HttpSession session)

    {   session.setMaxInactiveInterval(5000);

        int otp=(Integer) session.getAttribute("myotp");
        RegData regData= null;
        regData=(RegData)session.getAttribute("regData") ;
        RegData reg=(RegData) session.getAttribute("regData");
        System.out.println(regData);
        System.out.println(reg);
        if(userOtp==otp){



                        regData.setPassword(passwordEncoder.encode(regData.getPassword()));
                     regData.setRole(("ROLE_" + regData.getRole()).toUpperCase());
                         regRepository.save(regData);
                        System.out.println("otp verify");
                             return "Public/success";



                 }
        else {
            System.out.println("otp not verify");
                return "public/invalid";
    }

    }
}
