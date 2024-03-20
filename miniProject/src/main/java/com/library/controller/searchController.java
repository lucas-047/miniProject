package com.library.controller;

import com.library.dao.BookRepository;
import com.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


@RestController



public class searchController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal)
    {

        List<Book> book=this.bookRepository.findByBookNameContaining(query);
        return ResponseEntity.ok(book);
    }
}
