package com.example.testjpa.repo;

import com.example.testjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update User set status = ?2 where id = ?1")
    void updateStatus(Long id, Integer status);

}
