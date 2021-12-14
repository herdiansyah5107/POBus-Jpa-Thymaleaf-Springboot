package com.busspringboot.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.busspringboot.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);
    
    @Query(value="SELECT * FROM users WHERE email = ?1",nativeQuery=true)
    List<User> findEmail(String email);

    List<User> findByPassword(String password);
}
