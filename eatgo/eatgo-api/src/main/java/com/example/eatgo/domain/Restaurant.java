package com.example.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private List<MenuItem> menuItems;


    public String getInformation(){
        return name + " "+ address;
    }
    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

}
