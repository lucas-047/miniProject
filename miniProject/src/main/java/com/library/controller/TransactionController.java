package com.library.controller;

import com.library.Config.PenaltyService;
import com.library.Config.TransactionService;
import com.library.dao.BookRepository;
import com.library.dao.PenaltyRepository;
import com.library.dao.UserRepository;
import com.library.entities.Book;
import com.library.entities.Penalty;
import com.library.entities.Transaction;
import com.library.entities.User;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/issue-return")
    public String getissuepage(Model model) {
        model.addAttribute("Book", false);
        model.addAttribute("UserStatus", false);
        model.addAttribute("issueSuccess", false);
        model.addAttribute("successError", false);
        model.addAttribute("returnSuccess", false);
        model.addAttribute("Penalty", 0);
        model.addAttribute("BookStatus", false);
        model.addAttribute("alreadyReturn", false);

        return "Admin/IssueReturn";
    }

    @PostMapping("/Issue")
    public String validation(
            @RequestParam("user") String userId,
            @RequestParam("bookId") int bookId,
            Model model
    ) {
        model.addAttribute("Book", false);
        model.addAttribute("UserStatus", false);
        model.addAttribute("issueSuccess", false);
        model.addAttribute("successError", false);
        model.addAttribute("returnSuccess", false);
        model.addAttribute("Penalty", 0);
        model.addAttribute("BookStatus", false);
        model.addAttribute("alreadyReturn", false);
        boolean checkuser = userRepository.existsById(userId);
        Book book = bookRepository.getBookByBookId(bookId);
        if (checkuser && book != null) {
            int status = book.getBookStatus();
            if (status == 0) {
                System.out.println("book not available");
                model.addAttribute("BookStatus", true);
            } else {
                book.setBookStatus(0);
                bookRepository.saveAndFlush(book);
                LocalDate currentDate = LocalDate.now();
                LocalDate duedate = currentDate.plusDays(1);
                System.out.println("status changed");
                int transactionStatus = penaltyService.saveTempIssueTransaction(
                        userId,
                        bookId,
                        currentDate,
                        duedate
                );
                if (transactionStatus == 0) {
                    System.out.println("Transaction record saved");
                    List<Penalty> p = penaltyService.getUserData(userId);
                    Penalty user = p.getFirst();
                    model.addAttribute("user", user);
                    model.addAttribute("userdetail", p);
                    model.addAttribute("issueSuccess", true);
                } else {
                    System.out.println("Transaction record not saved");
                    model.addAttribute("successError", true);
                }
            }
        } else {
            if (book == null) {
                model.addAttribute("Book", true);
                System.out.println(checkuser);
            }
            if (!checkuser) {
                model.addAttribute("UserStatus", true);
            }
        }
        return "Admin/IssueReturn";
    }

    @PostMapping("/Return")
    public String returnProcess(@RequestParam("bookId") int bookId, Model model) {
        model.addAttribute("Book", false);
        model.addAttribute("UserStatus", false);
        model.addAttribute("issueSuccess", false);
        model.addAttribute("successError", false);
        model.addAttribute("returnSuccess", false);
        model.addAttribute("Penalty", 0);
        model.addAttribute("BookStatus", false);
        model.addAttribute("alreadyReturn", false);
        Boolean checkbook = bookRepository.existsById(bookId);
        int status = bookRepository.getBookstatus(bookId);
        if (status == 1) {
            System.out.println("book is already return");
            model.addAttribute("alreadyReturn", true);
        } else {
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
                if (day >= 0) {
                    System.out.println("you are early bird");
                    model.addAttribute("returnSuccess", true);
                    transactionService.transferPenaltyToTransaction(
                            Exportdata,
                            returnDate
                    );
                    List<Transaction> t = transactionService.getBookRecord(bookId);
                    List<String> id = new ArrayList<>();
                    for (Transaction transaction : t) {
                        String i = transaction.getUserId();
                        id.add(i);
                    }
                    List<User> u = userRepository.findAllById(id);
                    System.out.println(u);
                    model.addAttribute("username", u);
                    System.out.println(t);
                    model.addAttribute("bookdata", t);
                } else {
                    day = Math.abs(day);
                    int PenaltyDue = 10 * day;
                    System.out.println("ops you got penalty of " + PenaltyDue + "Rs");
                    model.addAttribute("Penalty", PenaltyDue);

                    transactionService.transferPenaltyToTransaction(
                            Exportdata,
                            returnDate
                    );
                }
                penaltyRepository.deleteById(bookId);
            } else {
                System.out.println("book id is wrong");
                model.addAttribute("Book", true);
            }
        }

        return "Admin/IssueReturn";
    }
}
