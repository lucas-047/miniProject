package com.library.dao;
import com.library.entities.userProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userProfileRepository extends JpaRepository<userProfile,Integer> {

    

}
