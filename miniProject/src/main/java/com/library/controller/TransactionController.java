package com.library.controller;
import com.library.Config.PenaltyService;
import com.library.Config.TransactionService;
import com.library.dao.BookRepository;
import com.library.dao.PenaltyRepository;
import com.library.entities.Book;
import com.library.entities.Penalty;
import com.library.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PenaltyService penaltyService;
    @Autowired
    private PenaltyRepository penaltyRepository;


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
                Book book=bookRepository.getBookByBookId(bookId);
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
               //  int transactionStatus= transactionService.saveIssueTransaction(userId,bookId,currentDate,duedate);
                 int transactionStatus= penaltyService.saveTempIssueTransaction(userId,bookId,currentDate,duedate);
            if(transactionStatus==0)
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
        {   Book book=bookRepository.getBookByBookId(bookId);

            System.out.println("return book found in database");
            book.setBookStatus(1);
            String string = book.toString();
            System.out.println(string);
            bookRepository.save(book);
            System.out.println("status change now book is available");
            LocalDate returnDate=LocalDate.now();
            Penalty penalty = penaltyRepository.getDuedate(bookId);
            LocalDate checkDuedate= penalty.getTempDueDate();
            int day= (int) ChronoUnit.DAYS.between(returnDate,checkDuedate);
            Optional<Penalty> penalty1=penaltyRepository.findById(bookId);
            Penalty Exportdata=penalty1.get();
            if(day==0)
            {
                System.out.println("on time");
                transactionService.transferPenaltytoTransactionWithoutPenalty(Exportdata);

            }
            else if(day>0)
            {
                System.out.println("you are early bird");
                transactionService.transferPenaltytoTransactionWithoutPenalty(Exportdata);
            }
            else {
               // transactionService.savePentaltyStatus(bookId,day);
                System.out.println("ops you got penalty");
            }

        transactionService.transferPenaltytoTransactionWithPenalty(Exportdata,returnDate);


        }
        else {
            System.out.println("book id is wrong");
        }


        return "Public/Success";
    }
}
