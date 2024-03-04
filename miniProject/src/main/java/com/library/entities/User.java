package com.library.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Bean;

@Entity
@Table(name = "User")

public class User {
    @Size(min=14,max=14, message = "Id should be 14 letter only..")
    @NotBlank(message ="This field cannot be blank")
    @Column(length = 16)
    @Id
    private double userId;
    @Column(length = 20)
    @Size(min=5,max=20, message = "First Name should be 5 to 12 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private String firstName;
    @Column(length = 30)
    @Size(min=5,max=30, message = "Last Name should be 5 to 12 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private  String lastName;
    @Column(length=15)
    @NotBlank(message ="This field cannot be blank")
    private String branch;
    @Column(length = 70)
    @Size(min=10,max=70, message = "Address should be 10 to 30 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private String address;
    @Column(length = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Mobile")
    @Size(min=10,max=10, message = "Mobile should be 10 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private String mobileNumber;
    @Column(length = 35)
    @NotBlank(message ="This field cannot be blank")
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$", message = "Invalid Email !!")
    private String email;
    private String PenaltyId;
    private String role;
    private String IssuedBook;


    public double getUserId() {
        return userId;
    }

    public void setUserId(double userId) {
        this.userId = userId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPenaltyId() {
        return PenaltyId;
    }

    public void setPenaltyId(String penaltyId) {
        PenaltyId = penaltyId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIssuedBook() {
        return IssuedBook;
    }

    public void setIssuedBook(String issuedBook) {
        IssuedBook = issuedBook;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", branch='" + branch + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", PenaltyId='" + PenaltyId + '\'' +
                ", role='" + role + '\'' +
                ", IssuedBook='" + IssuedBook + '\'' +
                '}';
    }
}
