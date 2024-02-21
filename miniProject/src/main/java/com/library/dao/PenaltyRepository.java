package com.library.dao;

import com.library.entities.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PenaltyRepository extends JpaRepository<Penalty,Integer> {
    @Query("select tempTransaction from Penalty tempTransaction where tempTransaction.tempBookId =:bookId ")
    public Penalty getDuedate(@Param("bookId") int bookId);
   // public Penalty getdatabyid(int bookid);
}
