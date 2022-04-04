package com.sparta.springadvanced_hh99homework.dto;

import com.sparta.springadvanced_hh99homework.model.Restaurant;
import lombok.Getter;

@Getter
public class FoodRequestDto {
    private Long id;

    private Integer quantity;

    private String name;

    private Integer price;
}
