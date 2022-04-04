package com.sparta.springadvanced_hh99homework.model;

import com.sparta.springadvanced_hh99homework.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderedFoodDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long dbId;

    @OneToOne
    @JoinColumn(nullable = false)
    private Food food;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Long orderedFoodTotalPrice;

    @ManyToOne
    @JoinColumn(nullable = false)
    private EachOrder eachOrder;

    public OrderedFoodDetail(Food food, FoodRequestDto foodRequestDto) {
        this.food = food;
        this.quantity = foodRequestDto.getQuantity();
        this.orderedFoodTotalPrice = 0L;
        this.eachOrder = null;
    }
}
