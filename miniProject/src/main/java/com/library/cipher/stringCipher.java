package com.library.cipher;

import org.jasypt.util.text.AES256TextEncryptor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class stringCipher {

    public String encode(String Plain)
    {
        AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
        aesEncryptor.setPassword("MR0pilBXUqlnDUiU");
        String encryptedValue = aesEncryptor.encrypt(Plain);
        return URLEncoder.encode(encryptedValue, StandardCharsets.UTF_8);
    }

    public String decode(String encoded)
    {
        AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
        aesEncryptor.setPassword("MR0pilBXUqlnDUiU");
        return aesEncryptor.decrypt(encoded);
    }

}