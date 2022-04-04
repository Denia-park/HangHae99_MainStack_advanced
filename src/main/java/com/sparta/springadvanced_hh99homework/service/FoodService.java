package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.Validator;
import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.FoodRepository;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    private final Validator validator;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, Validator validator, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.validator = validator;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public void registerFood(List<Food> foodList) {
        for (Food food : foodList) {
            validator.validateInput(food);

            if(foodRepository.existsByRestaurantAndName(food.getRestaurant(), food.getName()))
                throw new IllegalStateException("해당 음식점에 해당 음식 이름이 이미 존재합니다.");

            foodRepository.save(food);
        }
    }

    public List<Food> getFoods(Long restaurantId) {
        Restaurant findedRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new IllegalArgumentException("해당 하는 음식점 ID는 존재하지 않습니다."));
        return foodRepository.findAllByRestaurant(findedRestaurant);
    }
}
