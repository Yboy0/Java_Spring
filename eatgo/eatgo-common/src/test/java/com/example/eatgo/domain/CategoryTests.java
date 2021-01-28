package com.example.eatgo.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTests {

    @Test
    public void creation(){
        Category category = Category.builder().name("Japanese Food").build();

        assertThat(category.getName()).isEqualTo("Japanese Food");
    }
}