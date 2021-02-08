package com.example.friends.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@SpringBootTest
public class HelloControllerTests {

    @Autowired //spring context에서 bean을 주입하겠다는 의미
    private HelloController helloController;


    private MockMvc mockMvc;

    @Test
    public void helloWorld(){
        assertThat(helloController.helloWorld()).isEqualTo("hello");
    }

    @Test
    void mockMvcTest() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();

        mockMvc.perform(get("/api/hello")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }
}