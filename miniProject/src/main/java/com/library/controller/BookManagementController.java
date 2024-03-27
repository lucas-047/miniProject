package com.library.controller;

import com.library.dao.BookRepository;
import com.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookManagementController {
    @Autowired
    private BookRepository bookRepository;
    @RequestMapping("/bookdata")
public String getbookpage()
{
    return "Public/BookAdd";
}

@PostMapping("/add")
public String adddata(@ModelAttribute Book book)
{   book.setBookStatus(1);
    book.setCopyId(1);
    bookRepository.save(book);
    return "Public/timepass";
}
}
