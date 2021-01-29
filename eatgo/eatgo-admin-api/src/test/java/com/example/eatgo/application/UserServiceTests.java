package com.example.eatgo.application;

import com.example.eatgo.domain.CategoryRepository;
import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers(){

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder()
                .email("fbduddn97@naver.com")
                .name("Yboy")
                .level(3L)
                .build());
        //will return의 값을 주입
        given(userRepository.findAll())
                .willReturn(mockUsers);

        List<User> users = userService.getUsers();
        
        assertThat(users.get(0).getName()).isEqualTo("Yboy");
    }

    @Test
    public void addUser(){
        String email = "fbduddb97@naver.com";
        String name = "Yboy";

        User mockUser =(User.builder().email(email).name(name).build());

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(email,name);

        assertThat(user.getName()).isEqualTo(name);

    }

    @Test
    public void updateUser(){
        Long id =1004L;
        String email = "fbduddb97@naver.com";
        String name = "Yman";
        Long level = 100L;

        User mockUser = User.builder()
                .id(id)
                .email(email)
                .name("Yboy")
                .level(1L)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id,email,name,level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.isAdmin()).isEqualTo(true);
    }
    @Test
    public void deactiveUser(){

        Long id =1004L;
        String email = "fbduddb97@naver.com";
        String name = "Yboy";
        Long level = 100L;

        User mockUser = User.builder()
                .id(id)
                .email(email)
                .name(name)
                .level(level)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deactiveUser(id);

        verify(userRepository).findById(id);

        assertThat(user.isAdmin()).isEqualTo(false);
        assertThat(user.isActive()).isEqualTo(false);
    }



}