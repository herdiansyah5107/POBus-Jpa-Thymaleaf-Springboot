package com.busspringboot.dto.exceptions;

public class CustomExceptoon extends IllegalArgumentException{
    public CustomExceptoon(String msg) {
        super(msg);
    }
}
