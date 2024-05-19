package com.library.dao;

import com.library.entities.Requestmanagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface RequestmanagementRepository extends JpaRepository<Requestmanagement,Double> {

public Requestmanagement findByBookId(int bookId);
public Requestmanagement findByRequestId(Double id);
public boolean existsByBookId(int bookId);

@Query("select r from Requestmanagement r where r.bookId =:bookId")
public Requestmanagement.Status findstatusbybookId(int bookId);


}
