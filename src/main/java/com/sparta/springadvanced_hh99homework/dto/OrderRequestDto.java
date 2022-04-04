package com.sparta.springadvanced_hh99homework.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;

    private List<FoodRequestDto> foods;

}
