package com.library.controller;

import com.library.Config.ExcelSheetConfig;
import com.library.Config.ExcelSheetService;
import com.library.dao.BookRepository;
import com.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@Controller

public class
ExcelSheetController {
    @Autowired
    private ExcelSheetConfig excelSheetConfig;
    @Autowired
    private ExcelSheetService excelSheetService;
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/htmlfile")
    public String uploadhtml() {
        return "Public/upload";
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {


        try {
            //File f = (File) file;
            // this.excelSheetConfig.ConvertExceltoListofUser(file1.getInputStream());
            excelSheetService.importDataFromExcel(file);
            // this.excelSheetService.save(file);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(Map.of("message", "file is uploaded and data is saved"));
    }
}





//@RequestMapping("/book")
//public String getAllBooks(Model model)
//
//{   List<Book> book=excelSheetService.getALLBook();
//    //Book book1= (Book) bookRepository.findAll();
//    System.out.println(book);
//    model.addAttribute("data",book);
////    return this.excelSheetService.getALLBook();
//    return "Public/ExcelsheetDisplay";
//}

