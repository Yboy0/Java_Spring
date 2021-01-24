package com.example.eatgo.application;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTests {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .address("Seoul")
                .name("Bob zip")
                .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId()).isEqualTo(1004L);
    }

    @Test
    public void addRestaurant() {
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();


        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId()).isEqualTo(1234L);
    }
    @Test
    public void updateRestaurant(){

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob")
                .address("Seoul")
                .build();
        //DB에서 해당 id 값 바꾸고
        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));
        //정보 update
        restaurantService.updateRestaurant(
                1004L,"Sool zip","Busan");

        assertThat(restaurant.getName()).isEqualTo("Sool zip");
        assertThat(restaurant.getAddress()).isEqualTo("Busan");
    }

}