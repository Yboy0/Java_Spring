package com.example.eatgo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotNull
    private Long level;

    private String password;

    private Long restaurantId;

    public boolean isAdmin(){
        return level == 3L;
    }

    public boolean isActive() {
        //level이 0보다 큰 경우에만 true
        return level > 0;
    }

    public void deactivate(){
        level = 0L;
    }


    // restaurantId를 갖고 있으면 주인
    public boolean isRestaurantOwner() {
        return level == 1L;
    }
}

