package com.library.Config;
import com.library.entities.userProfile;
import com.library.dao.userProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userProfileService  {

  private userProfileRepo repo;
    public void saveDetail(userProfile user)
    {
    repo.save(user);
    }
}
