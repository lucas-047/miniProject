package com.library.Config;


import com.library.dao.BookRepository;
import com.library.dao.PenaltyRepository;
import com.library.dao.UserRepository;
import com.library.entities.Book;
import com.library.entities.BookWithAdditionalData;
import com.library.entities.Penalty;
import com.library.entities.User;
import org.apache.catalina.core.StandardContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdvanceConfigService advanceConfigService;


    public int saveTempIssueTransaction(String userid, int bookid, LocalDate issuedate, LocalDate duedate) {

        Penalty penalty = new Penalty();
        penalty.setTempUserId(userid);
        penalty.setTempBookId(bookid);
        penalty.setTempIssueDate(issuedate);
        penalty.setTempDueDate(duedate);
        penalty.setTempPenaltyStatus(0);
        penalty.setReNew(0);
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

    public List<Penalty> getUserData(String tempUserId) {     // // return penaltyRepository.findAll();
        //return penaltyRepository.getTransactionByUserId(tempUserId);
        return penaltyRepository.findByTempUserId(tempUserId);
        //return userRepository.findAll();
    }

    public LocalDate data(int bookId) {
        Penalty penalty = penaltyRepository.findduedate(bookId);
        LocalDate date = penalty.getTempDueDate();
        return date;
    }

    public List<BookWithAdditionalData> resultdata(String book, int type) {
        PenaltyService penaltyService = new PenaltyService(penaltyRepository);
        // BookRepository bookRepository;
        List<Book> books = new ArrayList<>();
        if (type == 2) {
            books = bookRepository.findAllByBookNameContaining(book);

        }
        if (type == 1) {
            books = bookRepository.findAllByAuthorNameContaining(book);
        }
//        System.out.println("book +"+books);
        List<BookWithAdditionalData> bookWithAdditionalData = new ArrayList<>();
        List<BookWithAdditionalData> list2 = new ArrayList<>();
//        BookWithAdditionalData bd=new BookWithAdditionalData();
        for (Book source : books) {
            BookWithAdditionalData bd = new BookWithAdditionalData();
            int i = source.getBookStatus();
            if (i == 0) {
                LocalDate d = penaltyService.data(source.getBookId());
                System.out.println("service " + d);
                bd.setBook(source);
                bd.setDueDate(d);
                list2.add(bd);
                System.out.println("object " + bd.getDueDate());
            } else {
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


    public void issue(int bookId, String userName,int reNew) {

        User checkuser = userRepository.getRegDataByusername(userName);

        Book book = bookRepository.getBookByBookId(bookId);

        String role = checkuser.getRole();
        int totalNumberOfBook = checkuser.getIssuedBook();
        int penaltyStatus = checkuser.getPenaltyStatus();

        boolean compare = false;
        int difference;
        if (penaltyStatus == 0) {
            if (role.equals("ROLE_STUDENT")) {
                difference = advanceConfigService.getUserDueDate();

            } else {
                difference = advanceConfigService.getFacultyDueDate();

            }

               // LocalDate duedate = currentDate.plusDays(difference);
               // System.out.println("status changed");
            Period period = Period.ofDays(difference);
               // penaltyRepository.updateDueDateBybookId(bookId,period);
            Optional<Penalty> penaltyOptional = penaltyRepository.findById(bookId);

            if (penaltyOptional.isPresent()) {
                Penalty penalty = penaltyOptional.get();
                System.out.println("old is "+penalty.getTempDueDate());
                LocalDate newDueDate = penalty.getTempDueDate().plus(period);
                penalty.setTempDueDate(newDueDate);
                penalty.setReNew(reNew);
                penaltyRepository.saveAndFlush(penalty);
                System.out.println("done update");

                System.out.println("new due is "+newDueDate);

            }

        }

    }
}