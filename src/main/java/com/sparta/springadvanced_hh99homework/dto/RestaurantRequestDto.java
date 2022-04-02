package com.sparta.springadvanced_hh99homework.dto;

import lombok.Getter;

@Getter
public class RestaurantRequestDto {
    private Long id;

    private String name;

    private Integer minOrderPrice;

    private Integer deliveryFee;
}
