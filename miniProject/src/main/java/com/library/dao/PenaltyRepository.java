package com.library.dao;

import com.library.entities.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PenaltyRepository extends JpaRepository<Penalty,Integer> {
    @Query("select tempTransaction from Penalty tempTransaction where tempTransaction.tempBookId =:bookId ")
    public Penalty getDuedate(@Param("bookId") int bookId);
   // public Penalty getdatabyid(int bookid);
//   @Query("select tempUserData from Penalty tempUserData where tempUserData.tempUserId =: tempUserId ")
//   public List<Penalty> getTransactionByUserId(@Param("userName") String tempUserId);

   public List<Penalty> findByTempUserId(String tempUserId);
   @Query("select p from Penalty p where p.tempBookId =:bookId ")
   public Penalty findduedate(@Param("bookId") int bookId);
}
