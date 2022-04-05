package com.sparta.springadvanced_hh99homework.model;

import com.sparta.springadvanced_hh99homework.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long dbId;

    @Column(nullable = false, unique = true)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer minOrderPrice;

    @Column(nullable = false)
    private Integer deliveryFee;

    @Column(nullable = false)
    private Integer x;

    @Column(nullable = false)
    private Integer y;

    public Restaurant(Long restaurantId, String name, Integer minOrderPrice, Integer deliveryFee, Integer x, Integer y) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
        this.x = x;
        this.y = y;
    }

    public Restaurant(RestaurantRequestDto requestDto) {
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
        this.x = requestDto.getX();
        this.y = requestDto.getY();
    }
}
