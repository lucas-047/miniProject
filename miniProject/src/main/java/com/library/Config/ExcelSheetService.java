package com.library.Config;


import com.library.dao.BookRepository;
import com.library.dao.UserRepository;
import com.library.entities.Book;
import com.library.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@EnableJpaRepositories
@Service
public class ExcelSheetService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
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
public void saveUser(MultipartFile file)
{
    try
    {
        List<User> user=ExcelSheetConfig.ConvertExceltoListofUser(file.getInputStream());
        this.userRepository.saveAll(user);
    }
    catch(IOException e)
    {
        throw new RuntimeException(e);
    }
}

}
