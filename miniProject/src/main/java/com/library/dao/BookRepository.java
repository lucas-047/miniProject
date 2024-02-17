package com.library.dao;

import com.library.entities.Book;
import com.library.entities.RegData;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query("select book from Book book where book.bookId =:bookId ")
    public Book getStatus(@Param("bookId") int bookId);


}
