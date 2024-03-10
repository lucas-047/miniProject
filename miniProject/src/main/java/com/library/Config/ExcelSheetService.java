package com.library.Config;


import com.library.dao.BookRepository;
import com.library.dao.UserRepository;
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
    @Autowired
    private UserRepository userRepository;
    public void bookDataSave(MultipartFile file)
    {
        try {
            List<Book> book = ExcelSheetConfig.importDataOfBookFromExcel(file);
            this.bookRepository.saveAll(book);
            book.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
public void UserDataSave(MultipartFile file)
{
    try{
        List<User> user=ExcelSheetConfig.importDataOfUserFromExcel(file);
        this.userRepository.saveAll(user);
        System.out.println("vraj");
    } catch (IOException e) {
        System.out.println(e);
    }
}
}


