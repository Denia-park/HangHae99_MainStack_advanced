package com.sparta.springadvanced_hh99homework.dto;

import com.sparta.springadvanced_hh99homework.model.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RestaurantResponseDto {
    private Long id;

    private String name;

    private Integer minOrderPrice;

    private Integer deliveryFee;

    public RestaurantResponseDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.minOrderPrice = restaurant.getMinOrderPrice();
        this.deliveryFee = restaurant.getDeliveryFee();
    }
}
