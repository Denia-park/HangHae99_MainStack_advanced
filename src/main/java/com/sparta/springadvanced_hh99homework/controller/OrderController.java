package com.sparta.springadvanced_hh99homework.controller;

import com.sparta.springadvanced_hh99homework.dto.FoodRequestDto;
import com.sparta.springadvanced_hh99homework.dto.OrderRequestDto;
import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.OrderFood;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.FoodRepository;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    private RestaurantRepository restaurantRepository;
    private FoodRepository foodRepository;

    @Autowired
    public OrderController(RestaurantRepository restaurantRepository, FoodRepository foodRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
    }

    @PostMapping("/order/request")
    public void orderRequest(@RequestBody OrderRequestDto orderRequestDto){
        OrderFood a = convertDtoToModel(orderRequestDto);
        System.out.println("gg");
    }

    public OrderFood convertDtoToModel(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(()-> new IllegalArgumentException("해당 음식점 ID는 존재하지 않습니다."));

        Map<Food,Integer> foodMap = new HashMap<Food,Integer>();
        for (FoodRequestDto food : orderRequestDto.getFoods()) {
            Food findedFood = foodRepository.findByRestaurantAndId(restaurant, food.getId())
                    .orElseThrow(()-> new IllegalArgumentException("해당 음식이 존재하지 않습니다."));
            foodMap.put(findedFood, food.getQuantity());
        }

        return new OrderFood(restaurant , foodMap);
    }
}
