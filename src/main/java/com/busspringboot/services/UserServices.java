package com.busspringboot.services;

import com.busspringboot.repository.UserRepo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServices implements UserDetailsService{

    private final static String USER_NOT_FOUND_MSG=
            "user with email %s not found";
    private final UserRepo userRepo;
    
    @Bean
    @Override
    public UserDetails loadUserByUsername(String email) 
        throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                    String.format(USER_NOT_FOUND_MSG, email)));
    }
    
}
