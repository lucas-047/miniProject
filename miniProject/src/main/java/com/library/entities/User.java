package com.library.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.Length;

@Entity
@Table(name = "User")
public class User {

    @Id
    private String userName;
    @Size(max = 12, message = "First Name should be 5 to 12 letter only..")
    @NotBlank(message = "This field cannot be blank")
    @Column(length = 20)
    private String firstName;
    @Size(max = 12, message = "Last Name should be 5 to 12 letter only..")
    @NotBlank(message = "This field cannot be blank")
    @Column(length = 35)
    private String lastName;
    @NotBlank(message = "This field cannot be blank")
    @Column(length = 20)
    private String branch;

    @NotBlank(message = "This field cannot be blank")
    @Column(length = 60)
    private String address;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Mobile")
    @NotBlank(message = "This field cannot be blank")
    @Column(length = 10)
    private String mobileNumber;
    @NotBlank(message = "This field cannot be blank")
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$", message = "Invalid Email !!")
    @Column(length = 40)
    private String email;
    @Column(length = 2)
    private String Role;
    private String password;
    private int issuedBook;
    private int penaltyStatus;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
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

    public int getIssuedBook() {
        return issuedBook;
    }

    public void setIssuedBook(int issuedBook) {
        this.issuedBook = issuedBook;
    }

    public int getPenaltyStatus() {
        return penaltyStatus;
    }

    public void setPenaltyStatus(int penaltyStatus) {
        this.penaltyStatus = penaltyStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", branch='" + branch + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", Role='" + Role + '\'' +
                ", password='" + password + '\'' +
                ", issuedBook=" + issuedBook +
                ", penaltyStatus=" + penaltyStatus +
                '}';
    }
}