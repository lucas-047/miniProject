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
import org.springframework.lang.NonNull;
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
    int val=1;

    @RequestMapping("admin/searchBook")
    public ModelAndView getsearchpage()
    {   ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Public/Search");
        return modelAndView;
    }

    @GetMapping("get/data/{query}/{type}")
    public ResponseEntity<List<String>> search(@PathVariable String query,@PathVariable int type)
    {
       // val=type;
        System.out.println("type is "+type);
        if(type==1)
        {
        List<String> book=this.bookRepository.searchqueryforauthor(query);
            System.out.println(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        else {

        List<String> book=this.bookRepository.searchquery(query);
            System.out.println(book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }

    }
    @GetMapping("get/result/{book}")
    public ResponseEntity<?> searchresult(@PathVariable String book)
    {
        System.out.println("this i s "+val);
        List<BookWithAdditionalData> resultData=penaltyService.resultdata(book,val);
//model.addAttribute("dataResult",resultData);
for(BookWithAdditionalData b:resultData)
{
    LocalDate n=b.getDueDate();
    String nb= String.valueOf(n);
    System.out.println("ghf "+nb);
}
        System.out.println("data is "+resultData);
       // System.out.println("result is "+model.getAttribute("dataResult"));
        return ResponseEntity.ok(resultData);
    }
}
