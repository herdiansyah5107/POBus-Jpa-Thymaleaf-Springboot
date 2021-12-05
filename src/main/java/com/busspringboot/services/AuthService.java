package com.busspringboot.services;

import com.busspringboot.model.AuthToken;
import com.busspringboot.model.User;
import com.busspringboot.repository.TokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthToken authToken) {
        tokenRepository.save(authToken);
    }

    public AuthToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }
    
}
