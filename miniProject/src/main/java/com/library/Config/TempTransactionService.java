package com.library.Config;

import com.library.dao.TempTransactionRepository;
import com.library.entities.TempTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service

public class TempTransactionService {
    @Autowired
    TempTransactionRepository tempTransactionRepository;
    TempTransaction tempTransaction=new TempTransaction();
    public int saveTempIssueTransaction(String userid, int bookid, LocalDate issuedate, LocalDate duedate)
    {
        tempTransaction.setTempUserId(userid);
        tempTransaction.setTempBookId(bookid);
        tempTransaction.setTempIssueDate(issuedate);
        tempTransaction.setTempDueDate(duedate);
        tempTransactionRepository.save(tempTransaction);

        return 0;

    }
}
