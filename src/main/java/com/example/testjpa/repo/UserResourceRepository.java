package com.example.testjpa.repo;

import com.example.testjpa.controller.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserResourceRepository extends JpaRepository<UserResource, Long> {

    @Modifying
//    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from UserResource ur where ur.id = ?1")
    void testDelete(Long id);

    @Modifying
//    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from user_resource ur where ur.id = ?1", nativeQuery = true)
    void testDeleteNative(Long id);

}
