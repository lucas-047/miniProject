package com.library.controller;

import com.library.dao.BookRepository;
import com.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class BookManagementController {
    @Autowired
    private BookRepository bookRepository;
    @RequestMapping("/bookdata")
public String getbookpage(Model model)
{
    model.addAttribute("book", new Book());
    return "Public/BookAdd";
}
    @RequestMapping("/deletebook")
    public String deletebookpage()
    {
        return "Public/DeleteBook";
    }


@PostMapping("/add")
public String adddata(@ModelAttribute("book") Book book,Model model)

{
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
    return "Public/BookAdd";
}
@PostMapping("/delete")
public String deletebook(@RequestParam("bookId") int bookId, Model model)
{   boolean id=bookRepository.existsById(bookId);
    if(id)
    {
        bookRepository.deleteById(bookId);
        return "Public/timepass";
    }
   else {
       String error="id not found";
       model.addAttribute("error",true);
       return "Public/DeleteBook";
    }
}
}

