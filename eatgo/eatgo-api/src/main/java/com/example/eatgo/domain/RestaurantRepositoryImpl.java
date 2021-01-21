package com.example.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

   private List<Restaurant> restaurants = new ArrayList<>();

   public RestaurantRepositoryImpl(){
       Restaurant restaurant1 = Restaurant.builder()
               .id(1L)
               .name("Bob")
               .address("Seoul")
               .menuItems(new ArrayList<>())
               .build();
       Restaurant restaurant2 = Restaurant.builder()
               .id(2L)
               .name("Bob")
               .address("Suwon")
               .build();
       MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl();
       restaurant1.addMenuItem(new MenuItem("kimchi"));
       restaurants.add(restaurant1);
       restaurants.add(restaurant2);
   }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
       return  restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
