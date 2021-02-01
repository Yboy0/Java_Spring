package com.example.eatgo.application;

public class EmailNotExistedException extends RuntimeException {

    public EmailNotExistedException(String email){
        super ("Email is not registerd:" +email);
    }

}
