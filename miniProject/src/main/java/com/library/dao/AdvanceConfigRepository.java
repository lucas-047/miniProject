package com.library.dao;

import com.library.entities.AdvanceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvanceConfigRepository extends JpaRepository<AdvanceConfig,Integer>
{

    public AdvanceConfig findById(int id);
    @Query("select advance.value from AdvanceConfig advance where advance.id =: id")
    public String findvalue(int id);
}
