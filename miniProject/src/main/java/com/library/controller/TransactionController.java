package com.library.controller;

import com.library.Config.TempTransactionService;
import com.library.Config.TransactionService;
import com.library.dao.BookRepository;
import com.library.dao.TempTransactionRepository;
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
    private TransactionService transactionService;
    @Autowired
    private TempTransactionService tempTransactionService;

@RequestMapping("/issue")
    public String getissuepage()
    {
        return "Public/issuepage";
    }
    @PostMapping("/verify")
    public String validation(@RequestParam("user") String userId,@RequestParam("bookId") int bookId, Model model)
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
                   LocalDate duedate=currentDate.plusDays(5);
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
}
