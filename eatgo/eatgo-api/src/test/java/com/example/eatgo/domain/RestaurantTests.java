package com.example.eatgo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTests {
    @Test
    public void create(){
        Restaurant restaurant = Restaurant.builder()
                .name("bob")
                .address("seoul")
                .id(1004L)
                .build();
        assertThat(restaurant.getInformation()).isEqualTo("bob seoul");
    }

}