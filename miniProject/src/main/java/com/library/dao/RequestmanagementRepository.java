package com.library.dao;

import com.library.entities.Requestmanagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RequestmanagementRepository extends JpaRepository<Requestmanagement,Double> {

public Requestmanagement findByBookId(int bookId);
public Requestmanagement findByRequestId(Double id);
public boolean existsByBookId(int bookId);


}
