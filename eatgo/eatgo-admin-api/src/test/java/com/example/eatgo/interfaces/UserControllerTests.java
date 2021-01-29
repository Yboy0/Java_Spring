package com.example.eatgo.interfaces;


import com.example.eatgo.application.UserService;
import com.example.eatgo.domain.User;
import org.apache.catalina.Group;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("fbduddn97@naver.com")
                .name("Yboy")
                .level(3L)
                .build());
        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Yboy")));
    }

    @Test
    public void create() throws Exception {
        String email = "fbduddb97@naver.com";
        String name = "Yboy";

       User user =(User.builder()
                .email(email)
                .name(name)
                .build());
        given(userService.addUser(email,name)).willReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"fbduddb97@naver.com\", \"name\": \"Yboy\"}"))
                .andExpect(status().isCreated());

        verify(userService).addUser(email,name);
    }
    @Test
    public void update() throws Exception {
        Long id =1004L;
        String email = "fbduddb97@naver.com";
        String name = "Yboy";
        Long level = 100L;


        mvc.perform(patch("/users/1004")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\": \"fbduddb97@naver.com\",\"name\": \"Yboy\"}"))
                .andExpect(status().isOk());

        verify(userService).updateUser(eq(id),eq(email),eq(name),eq(level));
    }
    @Test
    public void deactivate() throws Exception {
        mvc.perform(delete("/users/1004"))
                .andExpect(status().isOk());

        verify(userService).deactiveUser(1004L);
    }
}