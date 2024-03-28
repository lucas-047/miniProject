package com.library.dao;

import com.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query("select book from Book book where book.bookId =:bookId ")
    public Book getBookByBookId(@Param("bookId") int bookId);
    @Query("select book.BookStatus from Book book where book.bookId =:bookId ")
    public int getBookstatus(@Param("bookId") int bookId);
    //search

   @Query("SELECT DISTINCT b.bookName FROM Book b WHERE LOWER(b.bookName) LIKE LOWER(CONCAT('%', :name, '%')) ")
    //@Query("SELECT b FROM Book b WHERE b.bookName IN (SELECT DISTINCT b2.bookName FROM Book b2 WHERE LOWER(b2.bookName) LIKE LOWER(CONCAT('%', :name, '%')))")
    public List<String> searchquery(String name);
    @Query("SELECT b FROM Book b WHERE b.bookName LIKE LOWER(CONCAT('%', :name, '%')) AND b.BookStatus = 1")
   public List<Book> findbookwithbookstatus(String name);

    @Query("SELECT b FROM Book b WHERE b.bookName LIKE LOWER(CONCAT('%', :name, '%')) AND b.BookStatus = 0")
    public List<Book> findbookwithbookstatusWithDue(String name);
    public List<Book> findAllByBookNameContaining(String name);

//    public List<Book>findDistinctByBookNameContaining(String name);
}
