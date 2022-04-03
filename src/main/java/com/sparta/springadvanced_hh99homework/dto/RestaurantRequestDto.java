package com.sparta.springadvanced_hh99homework.dto;

import com.sparta.springadvanced_hh99homework.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantRequestDto {
    private Long id;

    private String name;

    private Integer minOrderPrice;

    private Integer deliveryFee;

    public RestaurantRequestDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.minOrderPrice = restaurant.getMinOrderPrice();
        this.deliveryFee = restaurant.getDeliveryFee();
    }
}
