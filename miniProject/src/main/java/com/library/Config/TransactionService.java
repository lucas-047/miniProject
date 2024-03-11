package com.library.Config;

import com.library.dao.PenaltyRepository;
import com.library.dao.TransactionRepository;
import com.library.dao.UserRepository;
import com.library.entities.Penalty;
import com.library.entities.Transaction;
import com.library.entities.User;
import com.sun.source.tree.ImportTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
     Transaction transaction=new Transaction();

    public int saveIssueTransaction(String userid, int bookid, LocalDate issuedate,LocalDate duedate)
    {
        transaction.setUserId(userid);
        transaction.setBookId(bookid);
        transaction.setIssueDate(issuedate);
        transaction.setDueDate(duedate);
        transaction.setPenaltyStatus(-1);
        transactionRepository.save(transaction);

        return 0;

    }
    public void transferPenaltyToTransaction(Penalty Exportdata,LocalDate returning)
    {
        Transaction Importdata=new Transaction();
        Importdata.setBookId(Exportdata.getTempBookId());
        Importdata.setPenaltyStatus(Exportdata.getTempPenaltyStatus());
        Importdata.setUserId(Exportdata.getTempUserId());
        Importdata.setIssueDate(Exportdata.getTempIssueDate());
        Importdata.setDueDate(Exportdata.getTempDueDate());
        Importdata.setReturnDate(returning);

        transactionRepository.saveAndFlush(Importdata);
    }
    public List<Transaction> getBookRecord(int bookId)
    {
        return transactionRepository.findByBookId(bookId);
    }
}
