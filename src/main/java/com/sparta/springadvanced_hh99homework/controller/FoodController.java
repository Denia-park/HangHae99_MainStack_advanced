package com.sparta.springadvanced_hh99homework.controller;

import com.sparta.springadvanced_hh99homework.dto.FoodRequestDto;
import com.sparta.springadvanced_hh99homework.dto.FoodResponseDto;
import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import com.sparta.springadvanced_hh99homework.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FoodController {

    private RestaurantRepository restaurantRepository;
    private FoodService foodService;

    @Autowired
    public FoodController(RestaurantRepository restaurantRepository, FoodService foodService) {
        this.restaurantRepository = restaurantRepository;
        this.foodService = foodService;
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId , @RequestBody List<FoodRequestDto> requestDtos){
        foodService.registerFood(convertDtoToModel(restaurantId, requestDtos));
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> getFoods(@PathVariable Long restaurantId){
        return convertModelsToDtos(foodService.getFoods(restaurantId));
    }

    public List<FoodResponseDto> convertModelsToDtos(List<Food> foodList){
        ArrayList<FoodResponseDto> foodResponseDtoList = new ArrayList<>();

        for (Food food : foodList) {
            foodResponseDtoList.add(new FoodResponseDto(food));
        }

        return foodResponseDtoList;
    }

    public List<Food> convertDtoToModel(Long restaurantId, List<FoodRequestDto> requestDtos) {
        ArrayList<Food> foodResponseDtoList = new ArrayList<>();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);

        if (restaurant == null) {
            throw new IllegalArgumentException("해당 음식점 ID는 존재하지 않습니다.");
        }

        for (FoodRequestDto food : requestDtos) {
            foodResponseDtoList.add(new Food(restaurant,food));
        }

        return foodResponseDtoList;
    }
}
