package com.example.eatgo.application;

import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        userService = new UserService(userRepository,passwordEncoder);
    }

    @Test(expected = EmailExistedException.class)
    public void registerUserWithExistedEmail(){

        String email = "test@example.com";
        String name = "Tester";
        String password = "test";

        User mockUser = User.builder().build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        userService.registerUser(email,name,password);

        verify(userRepository,never()).save(any());
    }

}