package com.example.eatgo.application;

import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Restaurant> getRestaurant(Long id){
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        return restaurant;
    }
    public Restaurant addRestaurant(Restaurant restaurant){
        Restaurant saved = restaurantRepository.save(restaurant);
        return saved ;
    }
}
