package com.sparta.springadvanced_hh99homework.controller;

import com.sparta.springadvanced_hh99homework.dto.FoodRequestDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodController {

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId , @RequestBody List<FoodRequestDto> requestDtos){
    }
}
