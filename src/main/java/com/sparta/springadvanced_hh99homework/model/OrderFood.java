package com.sparta.springadvanced_hh99homework.model;

import com.sparta.springadvanced_hh99homework.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderFood {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @ElementCollection //  Map 의 Value가 non-entities
    @CollectionTable(name="FoodAndQuantity")
    @MapKeyJoinColumn(name="FOOD_ID")
    @Column(name="QUANTITY")
//    HashMap<Food, Long> foodAndQuantity;
    Map<Food, Integer> foodAndQuantity;

    @Column(nullable = false)
    Integer deliveryFee;

    @Column(nullable = false)
    Integer totalPrice;

    public OrderFood(Restaurant restaurant, Map<Food, Integer> foodAndQuantity) {
        this.restaurant = restaurant;
        this.foodAndQuantity = foodAndQuantity;
        this.deliveryFee = null;
        this.totalPrice = null;
    }
}
