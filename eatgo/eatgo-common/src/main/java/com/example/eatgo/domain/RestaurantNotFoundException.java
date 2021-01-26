package com.example.eatgo.domain;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id) {
        super("Could not find restaurant" + id);
    }
}
