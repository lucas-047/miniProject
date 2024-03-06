package com.library.Service;

import org.springframework.stereotype.Service;

@Service
public class RegService {
    public boolean exist(String email){
//        return UserRepository.existsByEmail(email);
        return true;
    }
}
