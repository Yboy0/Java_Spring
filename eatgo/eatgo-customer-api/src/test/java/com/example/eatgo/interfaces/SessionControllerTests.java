package com.example.eatgo.interfaces;

import com.example.eatgo.application.EmailNotExistedException;
import com.example.eatgo.application.PasswordWrongException;
import com.example.eatgo.domain.User;
import com.example.eatgo.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserService userService;


    @Test
    public void createWithValidAttributes() throws Exception {
        Long id = 1004L;
        String name ="Tester";
        String email = "tester@example.com";
        String password = "test";

        User mockUser = User.builder()
                .id(id)
                .name(name)
                .build();

        given(userService.authenticate(email,password)).willReturn(mockUser);

        given(jwtUtil.createToken(id,name)).willReturn("header.payload.signature");


        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"name\":\"Tester\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/session"))
                .andExpect(content().string(
                        containsString("{\"accessToken\":\"header.payload.signature\"}")));

        verify(userService).authenticate(eq(email), eq(password));
    }
    @Test
    public void createWithNotExistedEmail() throws Exception {
        given(userService.authenticate("X@example.com","test"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"X@example.com\",\"name\":\"Tester\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("X@example.com"), eq("test"));
    }
    @Test
    public void createWithWrongPassword() throws Exception {
        given(userService.authenticate("tester@example.com","X"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"name\":\"Tester\",\"password\":\"X\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("tester@example.com"), eq("X"));
    }


}