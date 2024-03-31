package com.library.controller;

import com.library.Config.TransactionService;
import com.library.dao.BookRepository;
import com.library.dao.PenaltyRepository;
import com.library.dao.TransactionRepository;
import com.library.dao.UserRepository;
import com.library.entities.Book;
import com.library.entities.Penalty;
import com.library.entities.Transaction;
import com.library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BookManagementController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PenaltyRepository penaltyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @RequestMapping("/bookExcel")
    public String addBookbyExcel(Model model){
        model.addAttribute("pageTitle","Books");
        return "Admin/BookManagement/addBookbyExcel";
    }

    @RequestMapping("/bookdata")
    public String getbookpage(Model model)
    {
        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle","Books");
        return "Admin/BookManagement/BookAdd";
    }
    @RequestMapping("/deletebook")
    public String deletebookpage(Model model)
    {
        model.addAttribute("pageTitle","Books");
        return "Admin/BookManagement/DeleteBook";
    }
    @GetMapping("/recordbook/")
    public String recordbookpage(Model model)
    {
        model.addAttribute("pageTitle","Books");
        return "Admin/BookManagement/BookRecord";
    }


    @PostMapping("/add")
    public String adddata(@ModelAttribute("book") Book book,Model model)

    {
        model.addAttribute("pageTitle","Books");
        System.out.println("method running");
        int number= book.getCopyId();
        Book copy= bookRepository.findTop();
        if(copy==null)
        {
            book.setCopyId(0);
        }
        else {
            int i=copy.getCopyId();
            i++;
            book.setCopyId(i);
        }
        System.out.println(book);
        System.out.println("copy = "+number);
        book.setBookStatus(1);
        List<Book> b=new ArrayList<>(number);
        for(int i=1;i<=number;i++)
        { DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate publishDate = LocalDate.parse(book.getPublishDate().toString(), formatter);
            Book newbook=new Book();
            newbook.setCopyId(book.getCopyId());
            newbook.setBookStatus(book.getBookStatus());
            newbook.setBranch(book.getBranch());
            newbook.setVersion(book.getVersion());
            newbook.setPublisher(book.getPublisher());
            newbook.setBookName(book.getBookName());
            newbook.setAuthorName(book.getAuthorName());
            newbook.setPublishDate(book.getPublishDate());
            System.out.println("run loop "+i);
            b.add(newbook);
            System.out.println(newbook);
        }

        bookRepository.saveAllAndFlush(b);
        model.addAttribute("bookdata",b);
        bookRepository.save(book);
        return "Admin/BookManagement/BookAdd";
    }
    @PostMapping("/delete")
    public String deletebook(@RequestParam("bookId") int bookId, Model model)
    {
        model.addAttribute("pageTitle","Books");
        boolean id=bookRepository.existsById(bookId);
        if(id)
        {
            bookRepository.deleteById(bookId);
            return "Public/timepass";
        }
        else {
            String error="id not found";
            model.addAttribute("error",true);
            return "Admin/BookManagement/DeleteBook";
        }
    }
    @PostMapping("/recordbook/")
    public String recordbook(@RequestParam("bookId") int bookId, Model model)
    {
        model.addAttribute("pageTitle","Books");
        boolean id=bookRepository.existsById(bookId);
        if(id) {
            boolean checkTransaction = transactionRepository.existsByBookId(bookId);
            boolean checkPenaltyTransaction = penaltyRepository.existsById(bookId);
            System.out.println("penaly is "+checkPenaltyTransaction);
            System.out.println("trans is "+checkTransaction);
            if (checkTransaction || checkPenaltyTransaction) {



                List<Penalty> currentRecord = penaltyRepository.findAllByTempBookId(bookId);
                List<Transaction> oldRecord = transactionService.getBookRecord(bookId);
                int count = 0;
                for (Penalty p : currentRecord) {
                    Transaction t = new Transaction();
                    t.setPenaltyStatus(p.getTempPenaltyStatus());
                    t.setReturnDate(null);
                    t.setDueDate(p.getTempDueDate());
                    t.setIssueDate(p.getTempIssueDate());
                    t.setBookId(p.getTempBookId());
                    t.setPenaltyStatus(p.getTempPenaltyStatus());
                    oldRecord.add(count, t);
                    count++;

                }
//        currentRecord.add((Penalty) oldRecord);
                List<String> idUser = new ArrayList<>();
                for (Penalty transaction : currentRecord) {
                    String i = transaction.getTempUserId();
                    idUser.add(i);
                }
                System.out.println("data is " + oldRecord);
                List<User> u = userRepository.findAllById(idUser);
                model.addAttribute("username", u);
                model.addAttribute("oldRecord", "oldRecord");

                return "Admin/BookManagement/BookRecord";
            }
            else {
                System.out.println("book have no transaction................");
                model.addAttribute("notransaction",true);
                return "Admin/BookManagement/BookRecord";
            }
        }
        else {

            model.addAttribute("error",true);
            return "Admin/BookManagement/BookRecord";
        }
    }
}

