package com.example.demo.model.network.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemApiResponse {
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private BigDecimal price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private Long partnerId;
}
