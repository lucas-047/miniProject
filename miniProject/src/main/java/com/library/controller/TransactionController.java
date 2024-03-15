package com.library.controller;
import com.library.Config.PenaltyService;
import com.library.Config.TransactionService;
import com.library.dao.BookRepository;
import com.library.dao.PenaltyRepository;
import com.library.dao.UserRepository;
import com.library.entities.Book;
import com.library.entities.User;
import com.library.entities.Penalty;
import com.library.entities.Transaction;
import com.library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    @Autowired
    private UserRepository userRepository;


@RequestMapping("/issue")
    public String getissuepage()
    {
        return "Admin/IssueReturn";
    }
    @PostMapping("/verify")
    public String validation(@RequestParam("user") String userId, @RequestParam("bookId") int bookId, Model model) {

        boolean checkuser = userRepository.existsById(userId);
        boolean checkbook = bookRepository.existsById(bookId);
        if (checkuser) {

        if (checkbook) {
            System.out.println("book exist");
            Book book = bookRepository.getBookByBookId(bookId);
            int status = book.getBookStatus();
            if (status == 0) {
                System.out.println("book not available");
            } else {
                book.setBookStatus(0);
                bookRepository.saveAndFlush(book);
                LocalDate currentDate = LocalDate.now();
                LocalDate duedate = currentDate.plusDays(1);
                System.out.println("status changed");
                //  int transactionStatus= transactionService.saveIssueTransaction(userId,bookId,currentDate,duedate);
                int transactionStatus = penaltyService.saveTempIssueTransaction(userId, bookId, currentDate, duedate);
                if (transactionStatus == 0) {
                    System.out.println("Transaction record saved");
                } else {
                    System.out.println("Transaction record not saved");
                }

            }
        } else {
            System.out.println("book not found");
        }

    }else {
            System.out.println("User not found");
        }
        List<Penalty> p=penaltyService.getUserData(userId);
        Penalty user=p.getFirst();
        model.addAttribute("user",user);
            model.addAttribute("userdetail",p);
       // List<Penalty> p=penaltyService.getUserData("userId");
        //System.out.println(p);
        return "Public/Success";
    }
    @RequestMapping("return")
    public String returnPage()
    {
        return "Admin/IssueReturn";
    }
    @PostMapping("/returning")
    public String returnProcess(@RequestParam("bookId") int bookId,Model model)
    {
        Boolean checkbook= bookRepository.existsById(bookId);
        int status=bookRepository.getBookstatus(bookId);
        if(status==1)
        {
            System.out.println("book is already return");
        }
        else
        {


            if (checkbook) {
                Book book = bookRepository.getBookByBookId(bookId);

                System.out.println("return book found in database");
                String string = book.toString();
                System.out.println(string);
                book.setBookStatus(1);
                bookRepository.saveAndFlush(book);
                System.out.println("status change now book is available");
                LocalDate returnDate = LocalDate.now();
                Penalty penalty = penaltyRepository.getDuedate(bookId);

                LocalDate checkDuedate = penalty.getTempDueDate();
                int day = (int) ChronoUnit.DAYS.between(returnDate, checkDuedate);
                Optional<Penalty> penalty1 = penaltyRepository.findById(bookId);
                Penalty Exportdata = penalty1.get();
//            if(day==0)
//            {
//                System.out.println("on time");
//                transactionService.transferPenaltyToTransaction(Exportdata,returnDate);
//
//            }
                if (day >= 0) {
                    System.out.println("you are early bird");
                    transactionService.transferPenaltyToTransaction(Exportdata, returnDate);
                } else {
                    // transactionService.savePentaltyStatus(bookId,day);
                    day = Math.abs(day);
                    int PenaltyDue = 10 * day;
                    System.out.println("ops you got penalty of " + PenaltyDue + "Rs");
                    transactionService.transferPenaltyToTransaction(Exportdata, returnDate);
                }
                penaltyRepository.deleteById(bookId);
                List<Transaction> t = transactionService.getBookRecord(bookId);
                List<String> id = new ArrayList<>();
                for (Transaction transaction : t) {
                    String i = transaction.getUserId();
                    id.add(i);
                }
                List<User> u = userRepository.findAllById(id);
                //List<User> u=   userRepository.findAllById(id);
                System.out.println(u);
                model.addAttribute("username", u);
//                User user1=t.getUser
//                System.out.println(user);
                System.out.println(t);
                model.addAttribute("bookdata", t);


            } else {
                System.out.println("book id is wrong");
            }
        }
        return "Public/Success";
    }
}
