package com.busspringboot.dto.exceptions;

public class AuthFailException extends IllegalArgumentException{
    public AuthFailException(String msg) {
        super(msg);
    }
}
