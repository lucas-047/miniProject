package com.library.dao;

import com.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query("select book from Book book where book.bookId =:bookId ")
    public Book getBookByBookId(@Param("bookId") int bookId);
    @Query("select book.BookStatus from Book book where book.bookId =:bookId ")
    public int getBookstatus(@Param("bookId") int bookId);
    //search
    public List<Book>findByBookNameContaining(String name);
}
