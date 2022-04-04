package com.sparta.springadvanced_hh99homework.dto;

import com.sparta.springadvanced_hh99homework.model.Food;
import lombok.Getter;

@Getter
public class FoodResponseDto {
    private Long id;

    private String name;

    private Integer price;

    public FoodResponseDto(Food food) {
        this.id = food.getFoodId();
        this.name = food.getName();
        this.price = food.getPrice();
    }
}
