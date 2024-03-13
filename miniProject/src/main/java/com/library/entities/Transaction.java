package com.library.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="transactionId")
    private Double transactionId;

    @Column(name="userId")
    private String userId;
    @Column(name="bookId")
    private int bookId;
    @Column( columnDefinition = "DATE",name="issueDate")
    @Temporal(TemporalType.DATE)
    private LocalDate issueDate;
    @Column( columnDefinition = "DATE",name="dueDate")
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;
    @Column( columnDefinition = "DATE",name="returnDate")
    @Temporal(TemporalType.DATE)
    private LocalDate returnDate;
    @Column(name="penaltyStatus")
    private int penaltyStatus;


    public Double getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Double transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getPenaltyStatus() {
        return penaltyStatus;
    }

    public void setPenaltyStatus(int penaltyStatus) {
        this.penaltyStatus = penaltyStatus;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", penaltyStatus='" + penaltyStatus + '\'' +
                '}';
    }
}
