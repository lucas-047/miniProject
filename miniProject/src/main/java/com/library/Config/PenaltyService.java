package com.library.Config;


import com.library.dao.BookRepository;
import com.library.dao.PenaltyRepository;
import com.library.entities.Book;
import com.library.entities.BookWithAdditionalData;
import com.library.entities.Penalty;
import org.apache.catalina.core.StandardContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class PenaltyService {
    public PenaltyService(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    PenaltyRepository penaltyRepository;




    public int saveTempIssueTransaction(String userid, int bookid, LocalDate issuedate, LocalDate duedate) {

        Penalty penalty = new Penalty();
        penalty.setTempUserId(userid);
        penalty.setTempBookId(bookid);
        penalty.setTempIssueDate(issuedate);
        penalty.setTempDueDate(duedate);
        penalty.setTempPenaltyStatus(0);
        penaltyRepository.saveAndFlush(penalty);
        //applicationEventPublisher.publishEvent(new DatabaseRefresh(this));
        return 0;

    }

    public int savePentaltyStatus(int bookid, int penalty) {
        // System.out.println("data is "+s);
        Optional<Penalty> changeStatus = penaltyRepository.findById(bookid);


        //int i=p.getTempBookId();
        //  System.out.println("getbyid is  "+i);
        if (changeStatus.isPresent()) {
            Penalty changingStatus = changeStatus.get();
            changingStatus.setTempPenaltyStatus(penalty);
            penaltyRepository.saveAndFlush(changingStatus);
        } else {
            System.out.println("Entity with ID   not found.");
        }


        //  Penalty penalty1= penaltyRepository.getdatabyid(bookid);


        return 0;
    }
    public List<Penalty> getUserData(String tempUserId)
    {     // // return penaltyRepository.findAll();
            //return penaltyRepository.getTransactionByUserId(tempUserId);
           return penaltyRepository.findByTempUserId(tempUserId);
        //return userRepository.findAll();
    }
    public LocalDate data(int bookId)
    {
        Penalty penalty=penaltyRepository.findduedate(bookId);
        LocalDate date= penalty.getTempDueDate();
        return date;
    }

    public List<BookWithAdditionalData> resultdata(String book,int type)
    {   PenaltyService penaltyService=new PenaltyService(penaltyRepository);
       // BookRepository bookRepository;
        List<Book> books=new ArrayList<>();
        if(type==0)
        {
         books=bookRepository.findAllByBookNameContaining(book);

        }
        else {
            books=bookRepository.findAllByAuthorNameContaining(book);
        }
//        System.out.println("book +"+books);
        List<BookWithAdditionalData> bookWithAdditionalData=new ArrayList<>();
        List<BookWithAdditionalData> list2=new ArrayList<>();
//        BookWithAdditionalData bd=new BookWithAdditionalData();
        for (Book source : books) {
            BookWithAdditionalData bd=new BookWithAdditionalData();
            int i=source.getBookStatus();
            if(i==0)
            {
                LocalDate d=penaltyService.data(source.getBookId());
                System.out.println("service "+d);
                bd.setBook(source);
                bd.setDueDate(d);
                list2.add(bd);
                System.out.println("object "+bd.getDueDate());
            }
            else {
                bd.setBook(source);
                bd.setDueDate(null);
                bookWithAdditionalData.add(bd);
            }
            //target.setTargetField(source.getSourceField());
            // map other fields if needed

        }

        bookWithAdditionalData.addAll(list2);
        return bookWithAdditionalData;
    }
}