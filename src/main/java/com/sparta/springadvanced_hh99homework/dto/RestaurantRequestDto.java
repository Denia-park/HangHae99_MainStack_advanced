package com.sparta.springadvanced_hh99homework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantRequestDto {
    private String name;

    private Integer minOrderPrice;

    private Integer deliveryFee;

    private Integer x;

    private Integer y;
}
