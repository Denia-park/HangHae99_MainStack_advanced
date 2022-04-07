package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.Validator;
import com.sparta.springadvanced_hh99homework.exception.ErrorCode;
import com.sparta.springadvanced_hh99homework.exception.HGPrivateException;
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
                throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_USER_ID);

            long foodIdCounter = foodRepository.findAllByRestaurant(food.getRestaurant()).size() + 1;
            food.setFoodId(foodIdCounter);

            foodRepository.save(food);
        }
    }

    public List<Food> getFoods(Long restaurantId) {
        Restaurant findedRestaurant = restaurantRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new HGPrivateException(ErrorCode.NOT_FOUND_RESTAURANT_ID));
        return foodRepository.findAllByRestaurant(findedRestaurant);
    }
}
