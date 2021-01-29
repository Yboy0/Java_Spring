package com.example.eatgo.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests {

    @Test
    public void creation(){
        User user = User.builder()
                .email("fbduddn97@naver.com")
                .name("Yboy")
                .level(3L)
                .build();
        assertThat(user.getName()).isEqualTo("Yboy");
        assertThat(user.isAdmin()).isEqualTo(true);
    }
}
