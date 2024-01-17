package com.library.dao;

import com.library.entities.RegData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegRepository extends JpaRepository<RegData,Integer> {
    @Query("select u from RegData u where u.userName = :userName")
    public RegData getRegDataByusername(@Param("userName") String userName);
}
