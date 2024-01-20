package com.library.Config;
import com.library.entities.userProfile;
import com.library.dao.userProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class userProfileService  {

  private userProfileRepository repo;
    public void saveDetail(userProfile user)
    {
    repo.save(user);
    }
}
