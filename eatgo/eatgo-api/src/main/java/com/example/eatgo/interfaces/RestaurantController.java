package com.example.eatgo.interfaces;

import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.*;
import com.example.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        Restaurant restaurant = restaurantService.getRestaurant(id);
        //기본 정보 + 메뉴 정보

        //Restaurant restaurant = restaurantRepository.findById(id);

       // List<MenuItem> menuItems = restaurantService.findAllByRestaurant(id);
        // restaurant.setMenuItems(menuItems);
        return restaurant;
    }
}
