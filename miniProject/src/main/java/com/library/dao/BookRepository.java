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

   @Query("SELECT DISTINCT b.bookName FROM Book b WHERE LOWER(b.bookName) LIKE LOWER(CONCAT('%', :name, '%')) ")
    //@Query("SELECT b FROM Book b WHERE b.bookName IN (SELECT DISTINCT b2.bookName FROM Book b2 WHERE LOWER(b2.bookName) LIKE LOWER(CONCAT('%', :name, '%')))")
    public List<String> searchquery(String name);
   public List<Book> findAllByBookNameContaining(String name);

//    public List<Book>findDistinctByBookNameContaining(String name);
}
