package com.library;

//import com.library.cipher.PassChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MiniProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniProjectApplication.class, args);
        System.out.println("Project Running...");

    }
}
