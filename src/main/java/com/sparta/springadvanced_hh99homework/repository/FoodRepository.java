package com.sparta.springadvanced_hh99homework.repository;

import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {
    boolean existsByRestaurantAndName(Restaurant restaurant, String name);

    List<Food> findAllByRestaurant(Restaurant restaurant);

    Optional<Food> findByRestaurantAndId(Restaurant restaurant, Long id);
}
