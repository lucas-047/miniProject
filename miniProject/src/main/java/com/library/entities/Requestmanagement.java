package com.library.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Requestmanagement")
public class Requestmanagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="requestId")
private double requestId;
private int bookId;
private int userId;
private LocalDate issueDate;
private LocalDate requestDate;
private LocalDate approveDate;
private String Status;
private int count;

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public double getRequestId() {
        return requestId;
    }

    public void setRequestId(double requestId) {
        this.requestId = requestId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(LocalDate approveDate) {
        this.approveDate = approveDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Requestmanagement{" +
                "requestId=" + requestId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", issueDate=" + issueDate +
                ", requestDate=" + requestDate +
                ", approveDate=" + approveDate +
                ", Status='" + Status + '\'' +
                ", count=" + count +
                '}';
    }
}
