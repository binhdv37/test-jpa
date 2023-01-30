package com.example.testjpa.repo;

import com.example.testjpa.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    @Transactional
//    @Modifying
    @Modifying(clearAutomatically = true)
    @Query(value = "update Test set name = ?2 where id = ?1")
    void testModify(Long id, String name);

}
