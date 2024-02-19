package com.library.controller;

import com.library.dao.BookRepository;
import com.library.dao.TransactionRepository;
import com.library.entities.Book;
import com.library.entities.Transaction;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;

@Controller
public class TransactionController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TransactionRepository transactionRepository;

@RequestMapping("/issue")
    public String getissuepage()
    {
        return "Public/issuepage";
    }
    @PostMapping("/verify")
    public String validation(@RequestParam("user") String userid,@RequestParam("bookId") int bookId, Model model)
    {

            Boolean checkbook= bookRepository.existsById(bookId);

            if(checkbook)
            {
                System.out.println("book exist");

                int bookstatus;
                Book book=bookRepository.getStatus(bookId);
//                int status=bookRepository.getStatus(bookId) ;
               int status=book.getBookStatus();
               if(status==0)
               {
                   System.out.println("book not available");
               }
               else {
                   book.setBookStatus( 0);
                   bookRepository.save(book);
                   Transaction transaction=new Transaction();
                   transaction.setBookId(bookId);
                   transaction.setUserId(userid);
                   LocalDate currentDate = LocalDate.now();
                   LocalDate duedate=currentDate.plusDays(5);



                   Date currentDate1 = new Date();
                   transaction.setIssueDate(currentDate);
                   transaction.setDueDate(duedate);
                   transaction.setPenaltyStatus(-1);
                   transactionRepository.save(transaction);
            System.out.println("status changed");


               }

            }
            else {
                System.out.println("book not found");
            }



        return "Public/Success";
    }
}
