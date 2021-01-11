package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private Integer price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private LocalDateTime updatedBy;

    private Long partnerId;



    //1 : N

    //LAZY= 지연로딩, Eager = 즉시로딩

    //Lazy = SELECT * FROM item where id=?

    //EAGER = 1:1

}
