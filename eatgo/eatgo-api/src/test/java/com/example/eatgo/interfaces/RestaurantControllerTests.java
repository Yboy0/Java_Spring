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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
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
        restaurants.add(new Restaurant(1L,"Bob","Seoul",new ArrayList<>()));

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
        Restaurant restaurant=new Restaurant(1L,"Bob","Seoul",new ArrayList<>());
        restaurant.addMenuItem(new MenuItem("kimchi"));
        given(restaurantService.getRestaurant(1L)).willReturn(restaurant);

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
}