package com.library.Config;

import org.springframework.stereotype.Service;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.*;
import java.util.Properties;

@Service
public class EmailService {
    public boolean sendemail(String subject,String message,String to) {
        boolean f=false;
        String from="librarymanagement220124@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        System.out.println("properties" + properties);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        jakarta.mail.Session session= jakarta.mail.Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("librarymanagement220124@gmail.com","scbuwqkwnhjjdcgo");
            }
        });


        session.setDebug(true);
        MimeMessage m=new MimeMessage(session);
        try{
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
            // m.setText(message);
            m.setContent(message,"text/html; charset=utf-8");
            Transport.send(m);
            System.out.println("sent successfully............");
            f=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return f;
    }
}
