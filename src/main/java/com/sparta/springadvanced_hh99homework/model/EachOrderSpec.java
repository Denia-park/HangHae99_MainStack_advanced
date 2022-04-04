package com.sparta.springadvanced_hh99homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class EachOrderSpec {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long dbId;

    @Column(nullable = false, unique = true)
    private Long orderRequestId;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    Integer deliveryFee;

    @Column(nullable = false)
    Long OrderRequestTotalPrice;

    public EachOrderSpec(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.deliveryFee = 0;
        this.OrderRequestTotalPrice = 0L;
    }
}
