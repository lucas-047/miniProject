package com.library.controller;

import com.library.Config.PenaltyService;
import com.library.Config.TransactionService;
import com.library.dao.BookRepository;
import com.library.dao.PenaltyRepository;
import com.library.entities.Book;
import com.library.entities.BookWithAdditionalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin


@RestController

public class searchController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PenaltyService penaltyService;
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
    public ResponseEntity<?> searchresult(@PathVariable String book, Model model)
    {


        List<BookWithAdditionalData> resultData=penaltyService.resultdata(book);
//model.addAttribute("dataResult",resultData);
for(BookWithAdditionalData b:resultData)
{
    LocalDate n=b.getDueDate();
    String nb= String.valueOf(n);
    System.out.println("ghf "+nb);
}
       // System.out.println("result is "+model.getAttribute("dataResult"));
        return ResponseEntity.ok(resultData);
    }
}
