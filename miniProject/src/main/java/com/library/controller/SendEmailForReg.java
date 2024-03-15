package com.library.controller;

import com.library.Config.EmailService;
import com.library.Config.GeneratePassword;
import com.library.dao.UserRepository;
import com.library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class SendEmailForReg {
    @Autowired
    private UserRepository userRepository;
    @Autowired
   private GeneratePassword generatePassword;
    @Autowired
    private EmailService emailService;


    @RequestMapping("/register")
    public String GotoRegistration()
    {
        System.out.println("request send");
        return "Public/registerStudent";
    }


@PostMapping("/sendEmail")
    public String SendMail()
    {
        List<User> user=userRepository.findAll();

        for(User u:user)
        {
            String name=u.getFirstName();
            String username= String.valueOf(u.getUserName());
            String email=u.getEmail();
            String password=generatePassword.generatedPassword(email);
            if(password==null)
            {
                System.out.println("password not generated");
            }
            else {

            }
            String subject="Registration for New Student";
           // String message = "Dear "+name+",\n\n Welcome to the Library management system!,\n\n As requested, here are your account details to access our library system: "+"\n\n     UserName : "+username+"\n     password : "+password+"\n\n Please keep this information secure and do not share it with anyone.We recommend changing your password after your first login to something only you know.\n\n\nBest regards,\n Librarian";

           String message=      "<html>\n" +
                   "<head>\n" +
                   "    <title>Library Account Credentials</title>\n" +
                   "</head>\n" +
                   "<body>\n" +
                   "    <p>Dear " + name + ",</p>\n" +
                   "    <p>Welcome to the library management system " +
                "    <p>As requested, here are your account details to access our library system:</p>\n" +
                "    <ul>\n" +
                "        <li>Username: " + username + "</li>\n" +
                "        <li>Password: " + password + "</li>\n" +
                "    </ul>\n" +
                "    <p>Please keep this information secure and do not share it with anyone. We recommend changing your password after your first login to something only you know.</p>\n" +

                "    <p>Best regards,<br/>\n"+
                "        Librarian<br/>\n</p>\n"
                +
                "</body>\n" +
                "</html>";
            boolean flag = emailService.sendemail(subject, message, email);
            if (flag) {
                //yhh
                System.out.println("password send to  "+email);
                u.setPassword(password);
                userRepository.save(u);

            } else {
                System.out.println("Error in send password to  "+email);
//                model.addAttribute("notSend",true);
//                return "Public/forgotPassword";
            }
        }
        return "Public/timepass";
    }
}
