package com.sparta.springadvanced_hh99homework.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String minOrderPrice;

    @Column(nullable = false)
    private String deliveryFee;
}
