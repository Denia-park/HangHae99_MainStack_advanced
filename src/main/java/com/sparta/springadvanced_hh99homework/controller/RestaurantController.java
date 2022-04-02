package com.sparta.springadvanced_hh99homework.controller;

import com.sparta.springadvanced_hh99homework.dto.RestaurantRequestDto;
import com.sparta.springadvanced_hh99homework.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/register")
    public void registerRestaurant(@RequestBody RestaurantRequestDto requestDto){
        System.out.println("받은 값 :" + requestDto.getId() + " / " + requestDto.getName()+" / "+requestDto.getDeliveryFee()+" / "+requestDto.getMinOrderPrice());

    }

    @GetMapping("/restaurants")
    public void getRestaurants(){

    }
}