package com.busspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseDto {
    private String status;
    private String token;
    
    public SignInResponseDto(String status, String token) {
        this.status = status;
        this.token = token;
    }
    
    

}
