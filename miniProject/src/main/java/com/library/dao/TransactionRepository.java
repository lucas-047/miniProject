package com.library.dao;

import com.library.entities.Book;
import com.library.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query("select book from Book book where book.bookId =:bookId ")
    public Book getBookByBookId(@Param("bookId") int bookId);
        public List<Transaction> findByBookId(int bookId);
        public boolean existsByBookId(int bookId);
}
