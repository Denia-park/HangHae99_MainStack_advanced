package com.sparta.springadvanced_hh99homework.repository;

import com.sparta.springadvanced_hh99homework.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Optional<Restaurant> findByRestaurantId(Long RestaurantId);
    List<Restaurant> findAllByXBetweenAndYBetween(Integer startX,Integer endX,Integer startY,Integer endY);
}
