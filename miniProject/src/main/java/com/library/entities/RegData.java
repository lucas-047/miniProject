package com.library.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Registration")
public class RegData {

    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$", message = "*Invalid Email !!")
    @NotBlank(message = "*This is blank field")
    @Column(unique = true)
    private String email;
    @NotBlank(message ="*This field cannot be blank")
    private String password;
    @Id
    @Pattern(regexp = "^[0-9]{14}$", message = "*Username should be 14 letter only..")
    @NotBlank(message ="*This field cannot be blank")
    private String userName;
    @NotBlank(message ="*This field cannot be blank")
    private String role;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
                ", userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
