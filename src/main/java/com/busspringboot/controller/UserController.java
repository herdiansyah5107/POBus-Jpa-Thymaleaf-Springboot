package com.busspringboot.controller;

import com.busspringboot.dto.ResponseDto;
import com.busspringboot.dto.SignInDto;
import com.busspringboot.dto.SignInResponseDto;
import com.busspringboot.dto.SignupDto;
import com.busspringboot.dto.exceptions.AuthFailException;
import com.busspringboot.dto.exceptions.CustomExceptoon;
import com.busspringboot.model.AuthToken;
import com.busspringboot.model.User;
import com.busspringboot.repository.UserRepository;
import com.busspringboot.services.AuthService;
import com.busspringboot.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;
// import java.util.List;
import java.util.Objects;

// @RestController
// @RequestMapping("/user")
@Controller

public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto ) {
        return userService.signUp(signupDto);
    }

    @PostMapping("/masukk")
    public String masukk(@ModelAttribute("data")SignInDto signInDto, Model model) {
        User user = userRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(user)) {
            return "kenihilan";
        }
        //hash the pass
        try {
            if (!user.getPassword().equals(UserService.hashPassword(signInDto.getPassword()))) {
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
        
        return "index";
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

    @GetMapping("/login")
    public String login(Model model){
    model.addAttribute("data", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("data", new User());
            return "registrasi";
        }

    @PostMapping("/daftar")
    public String daftar(@ModelAttribute("data")SignupDto signupDto, Model model) {
        // check if user is already
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomExceptoon("User Already Present");
        }

        // hash the password
        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = UserService.hashPassword(signupDto.getPassword());
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

        return "index";
    }

}
