package com.library.Config;

import com.library.dao.RegRepository;
import com.library.entities.RegData;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CustomRegDetailsService implements UserDetailsService {
    @Autowired
    private RegRepository regRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegData regData = regRepository.getRegDataByusername(username);

        if(regData == null){
            throw new UsernameNotFoundException("User not found!!!");
        }
        CustomRegDetails customRegDetails = new CustomRegDetails(regData);
        return customRegDetails;
    }
}
