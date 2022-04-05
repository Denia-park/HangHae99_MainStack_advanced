package com.sparta.springadvanced_hh99homework.controller;

import com.sparta.springadvanced_hh99homework.dto.RestaurantRequestDto;
import com.sparta.springadvanced_hh99homework.dto.RestaurantResponseDto;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/register")
    public RestaurantResponseDto registerRestaurant(@RequestBody RestaurantRequestDto requestDto){
        Restaurant savedRestaurant = restaurantService.registerRestaurant(convertDtoToModel(requestDto));
        return new RestaurantResponseDto(savedRestaurant);
    }

    @GetMapping("/restaurants/all")
    public List<RestaurantResponseDto> getAllRestaurants(){
        return convertModelsToDtos(restaurantService.getRestaurants());
    }

    @GetMapping("/restaurants")
    public List<RestaurantResponseDto> getRestaurantsWithCordinates(@RequestParam Integer x , @RequestParam Integer y){
        System.out.println("좌표 :"+ x+","+y);
        return convertModelsToDtos(restaurantService.getRestaurants());
    }

    public Restaurant convertDtoToModel(RestaurantRequestDto requestDto){
        return new Restaurant(requestDto);
    }

    public List<RestaurantResponseDto> convertModelsToDtos(List<Restaurant> restaurantList){
        ArrayList<RestaurantResponseDto> restaurantResponseDtoList = new ArrayList<>();

        for (Restaurant restaurant : restaurantList) {
            restaurantResponseDtoList.add(new RestaurantResponseDto(restaurant));
        }

        return restaurantResponseDtoList;
    }
}
