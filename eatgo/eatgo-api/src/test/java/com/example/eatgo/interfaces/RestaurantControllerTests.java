package com.example.eatgo.interfaces;

//import jdk.internal.jshell.tool.ConsoleIOContext;
import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        //restaurantService = new RestaurantService(new RestaurantRepositoryImpl());
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1L)
                .name("Bob")
                .address("Seoul")
                .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

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
        Restaurant restaurant=Restaurant.builder()
                .id(1L)
                .name("Bob")
                .address("Seoul")
                .build();
        restaurant.setMenuItems(Arrays.asList(MenuItem.builder()
                .name("kimchi")
                .build()));

        given(restaurantService.getRestaurant(1L))
                .willReturn(java.util.Optional.of(restaurant));

        mvc.perform(get("/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob\"")
                ))
                .andExpect(content().string(
                        containsString("kimchi")
                ));
    }

    @Test
    public void create() throws Exception {
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Yboy\",\"address\":\"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/restaurants/null"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());


    }
    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaurants/1024")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"JOKER BAR\", \"address\":\"Seoul\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(1024L,"JOKER BAR","Busan");
    }
}