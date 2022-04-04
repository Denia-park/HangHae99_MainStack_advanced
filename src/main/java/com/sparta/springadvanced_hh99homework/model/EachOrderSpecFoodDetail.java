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
public class EachOrderSpecFoodDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long dbId;

    @OneToOne
    @JoinColumn(nullable = false)
    private Food food;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Long orderedEachFoodTotalPrice;

    @ManyToOne
    @JoinColumn(nullable = false)
    private EachOrderSpec eachOrderSpec;

    public EachOrderSpecFoodDetail(Food food, FoodRequestDto foodRequestDto) {
        this.food = food;
        this.quantity = foodRequestDto.getQuantity();
        this.orderedEachFoodTotalPrice = 0L;
        this.eachOrderSpec = null;
    }
}
