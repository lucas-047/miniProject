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
    public String uploadBook() {
        return "Public/upload";
    }
    @RequestMapping("/userfile")
    public String uploadUser() {
        return "Public/UserDataUpload";
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {


        if(ExcelSheetConfig.CheckFormat(file))
        {
            excelSheetService.bookDataSave(file);
            return ResponseEntity.ok(Map.of("message", "file is uploaded and data is saved"));
        }
        else{
            return ResponseEntity.ok(Map.of("message", "incorrect format"));
        }
    }
    @PostMapping(path = "/uploadUser", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadUser(@RequestParam("file") MultipartFile file) {

        if(ExcelSheetConfig.CheckFormat(file))
        {
            excelSheetService.UserDataSave(file);
            return ResponseEntity.ok(Map.of("message", "file is uploaded and data is saved"));
        }
        else{
            return ResponseEntity.ok(Map.of("message", "incorrect format"));
        }
    }
}

