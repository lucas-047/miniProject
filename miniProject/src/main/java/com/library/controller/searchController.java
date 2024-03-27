package com.library.controller;

import com.library.dao.BookRepository;
import com.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
@CrossOrigin


@RestController

public class searchController {
    @Autowired
    private BookRepository bookRepository;
    @RequestMapping("/search")
    public ModelAndView getsearchpage()
    {   ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Public/search");
        return modelAndView;
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<String>> search(@PathVariable String query)
    {

        List<String> book=this.bookRepository.searchquery(query);
        System.out.println(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @GetMapping("/search/result/{book}")
    public ResponseEntity<?> searchresult(@PathVariable String book)
    {
        List<Book> b=bookRepository.findAllByBookNameContaining(book);
        System.out.println("result is "+b);
        return ResponseEntity.ok(b);
    }
}
