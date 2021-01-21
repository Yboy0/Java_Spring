package com.example.eatgo.interfaces;

//import jdk.internal.jshell.tool.ConsoleIOContext;
import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.MenuItemRepository;
import com.example.eatgo.domain.MenuItemRepositoryImpl;
import com.example.eatgo.domain.RestaurantRepository;
import com.example.eatgo.domain.RestaurantRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @SpyBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @SpyBean(RestaurantRepositoryImpl.class)
    private  RestaurantRepository restaurantRepository;

    @Test
    public void list() throws Exception {
        restaurantService = new RestaurantService(new RestaurantRepositoryImpl());
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob\"")
                ));
    }
    @Test
    public void detail() throws Exception {
        mvc.perform(get("/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob\"")
                ))
                .andExpect(content().string(
                        containsString("\"MenuItem\":\"kimchi\"")
                ));
    }
}