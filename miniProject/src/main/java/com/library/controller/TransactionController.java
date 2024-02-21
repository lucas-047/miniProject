package com.library.controller;

import com.library.Config.TempTransactionService;
import com.library.Config.TransactionService;
import com.library.dao.BookRepository;
import com.library.dao.TempTransactionRepository;
import com.library.dao.TransactionRepository;
import com.library.entities.Book;
import com.library.entities.TempTransaction;
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
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Controller
public class TransactionController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TempTransactionService tempTransactionService;
    @Autowired
    private TempTransactionRepository tempTransactionRepository;


@RequestMapping("/issue")
    public String getissuepage()
    {
        return "Public/issuepage";
    }
    @PostMapping("/verify")
    public String validation(@RequestParam("user") String userId,@RequestParam("bookId") int bookId)
    {
            Boolean checkbook= bookRepository.existsById(bookId);
            if(checkbook)
            {
                System.out.println("book exist");
                Book book=bookRepository.getStatus(bookId);
               int status=book.getBookStatus();
               if(status==0)
               {
                   System.out.println("book not available");
               }
               else {
                   book.setBookStatus( 0);
                   bookRepository.save(book);
                   LocalDate currentDate = LocalDate.now();
                   LocalDate duedate=currentDate.plusDays(1);
                   System.out.println("status changed");
                 int transactionStatus= transactionService.saveIssueTransaction(userId,bookId,currentDate,duedate);
                 int tempTransactionStatus=tempTransactionService.saveTempIssueTransaction(userId,bookId,currentDate,duedate);
            if(transactionStatus==0 && tempTransactionStatus==0)
            {
                System.out.println("Transaction record saved");
            }
            else {
                System.out.println("Transaction record not saved");
            }

               }
            }
            else {
                System.out.println("book not found");
            }



        return "Public/Success";
    }
    @RequestMapping("return")
    public String returnPage()
    {
        return "Public/return";
    }
    @PostMapping("/returning")
    public String returnProcess(@RequestParam("bookId") int bookId)
    {
        Boolean checkbook= bookRepository.existsById(bookId);
        if(checkbook)
        {   Book book=bookRepository.getStatus(bookId);

            System.out.println("return book found in database");
            book.setBookStatus(1);
            String string = book.toString();
            System.out.println(string);
            bookRepository.save(book);
            System.out.println("status change now book is available");
            LocalDate returnDate=LocalDate.now();
            TempTransaction tempTransaction=tempTransactionRepository.getDuedate(bookId);
            LocalDate checkDuedate=tempTransaction.getTempDueDate();
            int day= (int) ChronoUnit.DAYS.between(returnDate,checkDuedate);
            if(day==0)
            {
                System.out.println("on time");
            }
            else if(day>0)
            {
                System.out.println("you are early bird");
            }
            else {
                System.out.println("ops you got penalty");
            }
        }
        else {
            System.out.println("book id is wrong");
        }


        return "Public/Success";
    }
}
