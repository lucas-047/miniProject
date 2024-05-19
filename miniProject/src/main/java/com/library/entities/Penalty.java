package com.library.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="PenaltyTransaction")
public class Penalty {



    private String tempUserId;
    @Id
    private int tempBookId;
    @Column( columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate tempIssueDate;
    @Column( columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate tempDueDate;
    @Column( columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate tempReturnDate;
    private int tempPenaltyStatus;
    private int reNew;



    public String getTempUserId() {
        return tempUserId;
    }

    public void setTempUserId(String tempUserId) {
        this.tempUserId = tempUserId;
    }

    public int getTempBookId() {
        return tempBookId;
    }

    public void setTempBookId(int tempBookId) {
        this.tempBookId = tempBookId;
    }

    public LocalDate getTempIssueDate() {
        return tempIssueDate;
    }

    public void setTempIssueDate(LocalDate tempIssueDate) {
        this.tempIssueDate = tempIssueDate;
    }

    public LocalDate getTempDueDate() {
        return tempDueDate;
    }

    public void setTempDueDate(LocalDate tempDueDate) {
        this.tempDueDate = tempDueDate;
    }


    public int getTempPenaltyStatus() {
        return tempPenaltyStatus;
    }

    public void setTempPenaltyStatus(int tempPenaltyStatus) {
        this.tempPenaltyStatus = tempPenaltyStatus;
    }

    public LocalDate getTempReturnDate() {
        return tempReturnDate;
    }

    public void setTempReturnDate(LocalDate tempReturnDate) {
        this.tempReturnDate = tempReturnDate;
    }

    public int getReNew() {
        return reNew;
    }

    public void setReNew(int reNew) {
        this.reNew = reNew;
    }

    @Override
    public String toString() {
        return "Penalty{" +
                "tempUserId='" + tempUserId + '\'' +
                ", tempBookId=" + tempBookId +
                ", tempIssueDate=" + tempIssueDate +
                ", tempDueDate=" + tempDueDate +
                ", tempReturnDate=" + tempReturnDate +
                ", tempPenaltyStatus=" + tempPenaltyStatus +
                ", reNew=" + reNew +
                '}';
    }
}
