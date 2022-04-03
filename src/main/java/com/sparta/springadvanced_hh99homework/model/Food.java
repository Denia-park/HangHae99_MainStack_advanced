package com.sparta.springadvanced_hh99homework.model;

import com.sparta.springadvanced_hh99homework.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    public Food(Restaurant restaurant, FoodRequestDto foodRequestDto) {
        this.restaurant = restaurant;
        this.name = foodRequestDto.getName();
        this.price = foodRequestDto.getPrice();
    }
}
