package com.library.dao;

import com.library.entities.Book;
import com.library.entities.TempTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TempTransactionRepository extends JpaRepository<TempTransaction,Integer> {
    @Query("select tempTransaction from TempTransaction tempTransaction where tempTransaction.tempBookId =:bookId ")
    public TempTransaction getDuedate(@Param("bookId") int bookId);
}
