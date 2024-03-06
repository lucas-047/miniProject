package com.library.Config;


import com.library.dao.BookRepository;
import com.library.entities.Book;
import com.library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelSheetService {
    @Autowired
    private BookRepository bookRepository;
    public void save(MultipartFile file)
    {
        try {
            List<Book> book = ExcelSheetConfig.convertExceltoList(file.getInputStream());
            this.bookRepository.saveAll(book);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Book> getALLBook()
    {
    return this.bookRepository.findAll();

    }

    public void saveUser(MultipartFile file){
        try{
            List<User> user = ExcelSheetConfig.ConvertExceltoListofUser(file.getInputStream());
        }catch (IOException error){
            System.out.println(error);
        }
    }

}
