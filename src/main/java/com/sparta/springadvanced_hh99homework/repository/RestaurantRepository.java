package com.sparta.springadvanced_hh99homework.repository;

import com.sparta.springadvanced_hh99homework.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Optional<Restaurant> findByName(String name);

    boolean existsByName(String name);
}
