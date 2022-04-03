package com.sparta.springadvanced_hh99homework.repository;

import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
    boolean existsByRestaurantAndName(Restaurant restaurant, String name);
}
