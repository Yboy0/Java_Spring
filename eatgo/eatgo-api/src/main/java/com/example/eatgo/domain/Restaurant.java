package com.example.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
   // private MenuItem menuItems;


    public String getInformation(){
        return name + " "+ address;
    }
//   public void addMenuItem(MenuItem menuItem){
//        menuItems.add(menuItem);
//    }

}
