package com.example.eatgo.interfaces;

import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.*;
import org.hibernate.annotations.Fetch;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
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
    public Optional<Restaurant> detail(@PathVariable("id") Long id){
        Optional<Restaurant> restaurant = restaurantService.getRestaurant(id);
        //기본 정보 + 메뉴 정보

        //Restaurant restaurant = restaurantRepository.findById(id);

       // List<MenuItem> menuItems = restaurantService.findAllByRestaurant(id);
        // restaurant.setMenuItems(menuItems);
        return restaurant;
    }
    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource)
            throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = Restaurant.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .build();

        Restaurant newRestaurant = restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/" + restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }
    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id ,
                       @RequestBody Restaurant resource){
        String name = resource.getName();
        String address =resource.getAddress();
        restaurantService.updateRestaurant(id,name,address);

        return "{}";

    }
}
