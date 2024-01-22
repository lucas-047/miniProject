package com.library.Service;

import com.library.dao.RegRepository;
import org.springframework.stereotype.Service;

@Service
public class RegService {
    public boolean exist(String email){
//        return RegRepository.existsByEmail(email);
        return true;
    }
}
