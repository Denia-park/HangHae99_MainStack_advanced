package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.Validator;
import com.sparta.springadvanced_hh99homework.dto.RestaurantRequestDto;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    private final Validator validator;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, Validator validator) {
        this.restaurantRepository = restaurantRepository;
        this.validator = validator;
    }

    public void registerRestaurant(Restaurant receivedRestaurant) {
        validator.validateRestaurantInput(receivedRestaurant);

        if(restaurantRepository.existsByName(receivedRestaurant.getName()))
            throw new IllegalStateException("해당 음식점의 이름이 이미 존재합니다.");

        restaurantRepository.save(receivedRestaurant);
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }
}
