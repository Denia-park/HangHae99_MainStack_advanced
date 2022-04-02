package com.sparta.springadvanced_hh99homework.model;

import com.sparta.springadvanced_hh99homework.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer minOrderPrice;

    @Column(nullable = false)
    private Integer deliveryFee;

    public Restaurant(RestaurantRequestDto requestDto) {
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }
}
