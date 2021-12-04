package com.busspringboot.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import com.busspringboot.dto.ResponseDto;
import com.busspringboot.dto.SignInDto;
import com.busspringboot.dto.SignInResponseDto;
import com.busspringboot.dto.SignupDto;
import com.busspringboot.dto.exceptions.AuthFailException;
import com.busspringboot.dto.exceptions.CustomExceptoon;
import com.busspringboot.model.AuthToken;
import com.busspringboot.model.User;
import com.busspringboot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        // check if user is already
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomExceptoon("User Already Present");
        }

        // hash the password
        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(
            signupDto.getUsername(), 
            signupDto.getEmail(), 
            encryptedpassword);

        userRepository.save(user);

        // create token
        final AuthToken authToken = new AuthToken(user);
        authService.saveConfirmationToken(authToken);

        ResponseDto responseDto = new ResponseDto("success", "CREATED SUCCESS!");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        //find user by email
        User user = userRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(user)) {
            throw new AuthFailException("User Is Not Valid!");
        }
        //hash the pass
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthFailException("wrong password!");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //compare pass in DB

        //if pass match
        AuthToken token = authService.getToken(user);

        //retrive token
        if (Objects.isNull(token)) {
            throw new CustomExceptoon("token is not present!");
        }
        
        return new SignInResponseDto("success", token.getToken());

    }
    
}
