package com.example.eatgo.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

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
        assertThat(user.isActive()).isEqualTo(true);

        user.deactivate();

        assertThat(user.isActive()).isEqualTo(false);
    }

    @Test
    public void restaurantOwner(){
        User user = User.builder()
                .level(50L).build();

        //가게 주인인지 확인
        assertThat(user.isRestaurantOwner()).isEqualTo(false);

        user.setLevel(1L);
        user.setRestaurantId(1004L);

        assertThat(user.isRestaurantOwner()).isEqualTo(true);
        assertThat(user.getRestaurantId()).isEqualTo(1004L);
    }

}
