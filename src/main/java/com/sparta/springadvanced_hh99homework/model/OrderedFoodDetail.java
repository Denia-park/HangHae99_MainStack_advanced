package com.sparta.springadvanced_hh99homework.model;

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
//    @JoinColumn(nullable = false)
    @JoinColumn(nullable = true)
    private Food food;

//    @Column(nullable = false)
    @Column(nullable = true)
    private Integer quantity;

//    @Column(nullable = false)
    @Column(nullable = true)
    private Long orderedFoodTotalPrice;

    public OrderedFoodDetail(Food findedFood, Integer quantity) {
        this.food = findedFood;
        this.quantity = quantity;
        this.orderedFoodTotalPrice = 0L;
    }
}
