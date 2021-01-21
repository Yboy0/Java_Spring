package com.example.eatgo.application;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantRepository;
import com.example.eatgo.domain.RestaurantRepositoryImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantServiceTest {

    private RestaurantRepository restaurantRepository =new RestaurantRepositoryImpl();
    private RestaurantService restaurantService=new RestaurantService(restaurantRepository);
    @Before
    public void setUp(){
        restaurantRepository = new RestaurantRepositoryImpl();
        restaurantService = new RestaurantService(restaurantRepository);
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1L);
        assertThat(restaurant.getId()).isEqualTo(1L);
        assertThat(restaurant.getMenuItems().get(0).getName()).isEqualTo("kimchi");
    }
    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        assertThat(restaurants.get(0).getId()).isEqualTo(1L);
    }

}