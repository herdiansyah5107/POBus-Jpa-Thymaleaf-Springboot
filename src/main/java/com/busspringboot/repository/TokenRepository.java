package com.busspringboot.repository;

import com.busspringboot.model.AuthToken;
import com.busspringboot.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthToken, Long>{

    AuthToken findByUser(User user);
    
}
