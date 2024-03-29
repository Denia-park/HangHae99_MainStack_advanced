package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.Validator;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Restaurant registerRestaurant(Restaurant receivedRestaurant) {
        validator.validateInput(receivedRestaurant);
        
        long restaurantIdCounter = restaurantRepository.findAll().size() + 1;
        receivedRestaurant.setRestaurantId(restaurantIdCounter);

        return restaurantRepository.save(receivedRestaurant);
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }
}
