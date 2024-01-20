package com.library.entities;

import jakarta.persistence.Entity;
import java.lang.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userProfile")
public class userProfile {
    @Id
    private int username;
   private String firstName;
   private String lastName;
   private String branch;
   private String email;
   private String mobileNo;
   private String address;
   private String role;
   private String totalIssuedBook;
   private String penaltyID;

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTotalIssuedBook() {
        return totalIssuedBook;
    }

    public void setTotalIssuedBook(String totalIssuedBook) {
        this.totalIssuedBook = totalIssuedBook;
    }

    public String getPenaltyID() {
        return penaltyID;
    }

    public void setPenaltyID(String penaltyID) {
        this.penaltyID = penaltyID;
    }

    @Override
    public String toString() {
        return "userProfile{" +
                "username=" + username +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", branch='" + branch + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                ", totalIssuedBook='" + totalIssuedBook + '\'' +
                ", penaltyID='" + penaltyID + '\'' +
                '}';
    }
}
