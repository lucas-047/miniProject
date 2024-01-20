package com.library.dao;
import com.library.entities.userProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userProfileRepo extends CrudRepository<userProfile,Integer> {

    

}
