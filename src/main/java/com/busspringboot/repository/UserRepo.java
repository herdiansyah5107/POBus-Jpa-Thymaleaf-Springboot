package com.busspringboot.repository;

import java.util.Optional;

import com.busspringboot.model.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo {
    Optional<User> findByEmail(String email);
}
