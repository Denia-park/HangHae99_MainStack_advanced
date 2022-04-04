package com.sparta.springadvanced_hh99homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderRequest {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long dbId;

    @Column(nullable = false, unique = true)
    private Long orderRequestId;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @OneToMany
    @JoinColumn(name = "ORDERED_FOOD_DETAIL_ID", nullable = false)
    private List<OrderedFoodDetail> orderedFoodDetailList;

    @Column(nullable = false)
    Integer deliveryFee;

    @Column(nullable = false)
    Long OrderRequestTotalPrice;

    public OrderRequest(Restaurant restaurant, List<OrderedFoodDetail> orderedFoodDetailList) {
        this.restaurant = restaurant;
        this.orderedFoodDetailList = orderedFoodDetailList;
        this.deliveryFee = 0;
        this.OrderRequestTotalPrice = 0L;
    }
}
