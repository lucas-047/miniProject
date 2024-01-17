package com.library.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {
    @Size(min=14,max=14, message = "Id should be 14 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private String userId;
    @Size(min=5,max=12, message = "First Name should be 5 to 12 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private String firstName;
    @Size(min=5,max=12, message = "Last Name should be 5 to 12 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private  String lastName;
    @NotBlank(message ="This field cannot be blank")
    private String branch;
    @Size(min=10,max=70, message = "Address should be 10 to 30 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private String address;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Mobile")
    @Size(min=10,max=10, message = "Mobile should be 10 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private String mobileNumber;
    @NotBlank(message ="This field cannot be blank")
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$", message = "Invalid Email !!")
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    @Override
    public String toString() {
        return "User{" +
                "UserId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", branch='" + branch + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
