package com.library.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Usertop")
public class User implements UserDetails{

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

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;
    private int issuedBook;
    private int penaltyStatus;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
