package com.library.Config;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service

public class GeneratePassword {
public String generatedPassword(String email)
{
    try {
        //String email="supervasu25@gmail.com";
        String modifiedEmail = email.replace("@gmail.com", "");
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(modifiedEmail.getBytes());
        byte[] digest = md.digest();

        // Encode the hash bytes using Base64 to generate a password
        String password = Base64.getEncoder().encodeToString(digest);
        String modifiedPassword=password.substring(0, 8);
        System.out.println("GENERATED IS "+modifiedPassword);
        return modifiedPassword;

    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
            return null;
    }



}


}
