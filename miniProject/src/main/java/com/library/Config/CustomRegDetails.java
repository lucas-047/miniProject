package com.library.Config;

import com.library.entities.RegData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class CustomRegDetails implements UserDetails {
    @Autowired
    private RegData regData;
    public CustomRegDetails(RegData regData) {
        super();
        this.regData = regData;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(regData.getRole());
        return List.of(simpleGrantedAuthority);
    }
    @Override
    public String getPassword() {
        return regData.getPassword();
    }

    @Override
    public String getUsername() {
        return regData.getUserName();
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
