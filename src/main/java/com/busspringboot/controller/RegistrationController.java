package com.busspringboot.controller;

import com.busspringboot.services.RegistrationService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    
    private RegistrationService registrationService;
    public String register(@RequestBody RegistrationService request) {
        return registrationService.register(request);
    }
}
