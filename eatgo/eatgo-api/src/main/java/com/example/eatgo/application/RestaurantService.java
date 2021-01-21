package com.example.eatgo.application;

import com.example.eatgo.domain.MenuItemRepository;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantRepository;
import com.example.eatgo.domain.RestaurantRepositoryImpl;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;


    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepository.findById(id);
        return restaurant;
    }
}
