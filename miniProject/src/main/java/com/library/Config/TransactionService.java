package com.library.Config;

import com.library.dao.TransactionRepository;
import com.library.entities.Penalty;
import com.library.entities.Transaction;
import com.sun.source.tree.ImportTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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
        transaction.setPenaltyStatus(-1);
        transactionRepository.save(transaction);

        return 0;

    }
    public void transferPenaltyToTransaction(Penalty Exportdata,LocalDate paymentDate)
    {
        Transaction Importdata=new Transaction();
        Importdata.setBookId(Exportdata.getTempBookId());
        Importdata.setPenaltyStatus(Exportdata.getTempPenaltyStatus());
        Importdata.setUserId(Exportdata.getTempUserId());
        Importdata.setIssueDate(Exportdata.getTempReturnDate());
        Importdata.setReturnDate(Exportdata.getTempReturnDate());
        Importdata.setDueDate(Exportdata.getTempDueDate());
        Importdata.setPaymentDate(paymentDate);
        transactionRepository.saveAndFlush(Importdata);
    }
    public void transferPenaltyToTransaction(Penalty Exportdata)
    {
        Transaction Importdata=new Transaction();
        Importdata.setBookId(Exportdata.getTempBookId());
        Importdata.setPenaltyStatus(Exportdata.getTempPenaltyStatus());
        Importdata.setUserId(Exportdata.getTempUserId());
        Importdata.setReturnDate(Exportdata.getTempReturnDate());
        Importdata.setReturnDate(Exportdata.getTempReturnDate());
        Importdata.setDueDate(Exportdata.getTempDueDate());
        transactionRepository.saveAndFlush(Importdata);
    }
}
