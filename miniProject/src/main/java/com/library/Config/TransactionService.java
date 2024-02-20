package com.library.Config;

import com.library.dao.TransactionRepository;
import com.library.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
     Transaction transaction=new Transaction();
    public int saveIssueTransaction(String userid, int bookid, LocalDate issuedate,LocalDate duedate)
    {
        transaction.setUserId(userid);
        transaction.setBookId(bookid);
        transaction.setIssueDate(issuedate);
        transaction.setDueDate(duedate);
        transactionRepository.save(transaction);

        return 0;

    }
}
