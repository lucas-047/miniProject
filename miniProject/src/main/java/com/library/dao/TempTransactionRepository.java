package com.library.dao;

import com.library.entities.TempTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempTransactionRepository extends JpaRepository<TempTransaction,Integer> {
}
