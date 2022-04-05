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
    public List<RestaurantResponseDto> getRestaurantsWithCordinates(@RequestParam(name = "x",required = false) Integer requestX , @RequestParam(name = "y",required = false) Integer requestY){
        if(requestX == null && requestY == null){
            return getAllRestaurants();
        }else if(requestX != null && requestY != null){
            System.out.println("좌표 :"+ requestX+","+requestY);

            return convertModelsToDtos(restaurantService.getRestaurantsWithCordinates(requestX,requestY));
        }else {
            throw new IllegalArgumentException("X,Y 모두 있어야 좌표를 구할수 있습니다.");
        }
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
