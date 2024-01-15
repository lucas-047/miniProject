package com.library.entities;

import jakarta.validation.constraints.*;

public class RegData {
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$", message = "Invalid Email !!")
    @NotBlank(message = "this is blank field")
    private String email;
    @NotBlank(message ="This field cannot be blank")
    private String password;
    @Size(min=5,max=12, message = "Username should be 5 to 12 letter only..")
    @NotBlank(message ="This field cannot be blank")
    private String user;
    @AssertTrue(message = "Must agree with term and condition")
    private boolean agreed;

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", user='" + user + '\'' +
                ", agreed=" + agreed +
                '}';
    }
}
