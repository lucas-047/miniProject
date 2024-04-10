package com.library.controller;

import com.library.Config.AdvanceConfigService;
import com.library.Config.CustomUserDetails;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private AdvanceConfigService advanceConfigService;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    private void Attribute(Model model) {
        int price = advanceConfigService.getPenaltyValue();
        model.addAttribute("Book", false);
        model.addAttribute("UserStatus", false);
        model.addAttribute("issueSuccess", false);
        model.addAttribute("successError", false);
        model.addAttribute("returnSuccess", false);
        model.addAttribute("Penalty", 0);
        model.addAttribute("BookStatus", false);
        model.addAttribute("alreadyReturn", false);
        model.addAttribute("maxIssue", false);
    }

    @GetMapping("/issue-return")
    public String getissuepage(Model model) {
        Attribute(model);
        System.out.println(authentication.getName());
        model.addAttribute("pageTitle", "Books");
        return "Admin/IssueReturn";
    }

    @PostMapping("/Issue")
    public String validation(
            @RequestParam("user") String userName,
            @RequestParam("bookId") int bookId,
            Model model
    ) {
        Attribute(model);
        User checkuser = userRepository.getRegDataByusername(userName);

        Book book = bookRepository.getBookByBookId(bookId);
        if (checkuser != null) {
            if (book != null) {
                int status = book.getBookStatus();
                if (status == 0) {
                    System.out.println("book not available");
                    model.addAttribute("BookStatus", true);
                } else {
                    book.setBookStatus(0);
                    LocalDate currentDate = LocalDate.now();
                    String role = checkuser.getRole();
                    int totalNumberOfBook = checkuser.getIssuedBook();
                    int penaltyStatus = checkuser.getPenaltyStatus();

                    boolean compare = false;
                    int difference;
                    int userLimitOfBook;
                    int facultyLimitOfBook;
                    if (penaltyStatus == 0) {
                        if (role.equals("ROLE_STUDENT")) {
                            difference = advanceConfigService.getUserDueDate();
                            userLimitOfBook =
                                    advanceConfigService.getNumberOfIssueBookForUser();
                            if (totalNumberOfBook < userLimitOfBook) {
                                compare = true;
                            }
                        } else {
                            difference = advanceConfigService.getFacultyDueDate();
                            facultyLimitOfBook =
                                    advanceConfigService.getNumberOfIssueBookForFaculty();
                            if (totalNumberOfBook < facultyLimitOfBook) {
                                compare = true;
                            }
                        }
                        if (compare) {
                            LocalDate duedate = currentDate.plusDays(difference);
                            System.out.println("status changed");
                            int transactionStatus = penaltyService.saveTempIssueTransaction(
                                    userName,
                                    bookId,
                                    currentDate,
                                    duedate
                            );
                            if (transactionStatus == 0) {
                                bookRepository.saveAndFlush(book);
                                checkuser.setIssuedBook((checkuser.getIssuedBook()+1));
                                userRepository.saveAndFlush(checkuser);
                                System.out.println("Transaction record saved");
                                List<Penalty> p = penaltyService.getUserData(userName);
                                Penalty user = p.getFirst();
                                model.addAttribute("user", user);
                                model.addAttribute("userdetail", p);
                                model.addAttribute("issueSuccess", true);
                            } else {
                                System.out.println("Transaction record not saved");
                                model.addAttribute("successError", true);
                            }
                        } else {
                            System.out.println("you issued maximum number of book");
                            model.addAttribute("maxIssue", true);
                        }
                    } else {
                        System.out.println("Please clear Penalty first!");
                    }
                }
            } else {
                model.addAttribute("Book", true);
            }
        } else {
            System.out.println("user not found");
            model.addAttribute("UserStatus", true);
        }
        model.addAttribute("pageTitle", "Books");
        return "Admin/IssueReturn";
    }



    @PostMapping("/Return")
    public String returnProcess(@RequestParam("bookId") int bookId, Model model) {
        Attribute(model);
        int price = advanceConfigService.getPenaltyValue();
        Boolean checkbook = bookRepository.existsById(bookId);
        int status = bookRepository.getBookstatus(bookId);

        User user = userRepository.getRegDataByusername(penaltyRepository.getDuedate(bookId).getTempUserId());
        System.out.println(user.getIssuedBook());
        System.out.println(user.getFirstName());
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
                    user.setIssuedBook((user.getIssuedBook()-1));
                    userRepository.saveAndFlush(user);
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
                    int PenaltyDue = price * day;
                    System.out.println("ops you got penalty of " + PenaltyDue + "Rs");
                    model.addAttribute("Penalty", PenaltyDue);

                    transactionService.transferPenaltyToTransaction(
                            Exportdata,
                            returnDate,
                            day
                    );
                    bookRepository.saveAndFlush(book);

                }
                penaltyRepository.deleteById(bookId);
            } else {
                System.out.println("book id is wrong");
                model.addAttribute("Book", true);
            }
        }
        model.addAttribute("pageTitle", "Books");
        return "Admin/IssueReturn";
    }
}
