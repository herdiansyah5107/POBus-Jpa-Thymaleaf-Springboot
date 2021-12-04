package com.busspringboot.repository;

import org.springframework.stereotype.Repository;

import com.busspringboot.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);
    
}
