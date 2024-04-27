package com.library.dao;

import com.library.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,String> {
    @Query("select u from User u where u.userName = :userName")
    public User getRegDataByusername(@Param("userName") String userName);

    @Query("select u from User u where u.email =:email")
    public User findByEmail(@Param("email") String email);
    @Query("select u.penaltyStatus from User u where u.userName =:name")
    public int findpenaltybyusername(String name);
  //  public UserDetails findByUsername(String username);
}
