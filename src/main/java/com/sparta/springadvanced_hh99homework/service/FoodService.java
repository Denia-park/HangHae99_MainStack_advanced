package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.Validator;
import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    private final Validator validator;

    @Autowired
    public FoodService (FoodRepository foodRepository, Validator validator) {
        this.foodRepository = foodRepository;
        this.validator = validator;
    }

    public void registerFood(List<Food> foodList) {
        for (Food food : foodList) {
            validator.validateInput(food);

            if(foodRepository.existsByRestaurantAndName(food.getRestaurant(), food.getName()))
                throw new IllegalStateException("해당 음식점에 이름이 이미 존재합니다.");

            foodRepository.save(food);
        }
    }

    public List<Food> getRestaurants() {
        return foodRepository.findAll();
    }
}
