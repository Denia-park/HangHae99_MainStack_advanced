package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.Validator;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    private final Validator validator;

    private final Integer MAX_DISTANCE_UNIT_KM = 3;
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

    public List<Restaurant> getRestaurantsWithCordinates(Integer requestX, Integer requestY) {
        Integer startX = requestX - MAX_DISTANCE_UNIT_KM;
        Integer endX = requestX + MAX_DISTANCE_UNIT_KM;
        Integer startY = requestY - MAX_DISTANCE_UNIT_KM;
        Integer endY = requestY + MAX_DISTANCE_UNIT_KM;

        List<Restaurant> findAllByRestaurantByCordinates = restaurantRepository.findAllByXBetweenAndYBetween(startX, endX, startY, endY);

        return getCalculatedRestaurantsListByDistance(findAllByRestaurantByCordinates, requestX, requestY);
    }

    private List<Restaurant> getCalculatedRestaurantsListByDistance(List<Restaurant> findAllByRestaurantByCordinates, Integer requestX, Integer requestY) {
        List<Restaurant> returnRestaurantList = new ArrayList<>();
        for (Restaurant findedRestaurant : findAllByRestaurantByCordinates) {
            long diffBetweenRestaurantXAndRequestX = Math.abs(findedRestaurant.getX() - requestX);
            long diffBetweenRestaurantYAndRequestY = Math.abs(findedRestaurant.getY() - requestY);
            if (diffBetweenRestaurantXAndRequestX + diffBetweenRestaurantYAndRequestY <= MAX_DISTANCE_UNIT_KM) {
                returnRestaurantList.add(findedRestaurant);
            }
        }

        return returnRestaurantList;
    }
}
