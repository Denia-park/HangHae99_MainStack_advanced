package com.sparta.springadvanced_hh99homework.testdata;

import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class testDataRunner implements ApplicationRunner {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public testDataRunner(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 테스트 User 생성
        Restaurant testRestaurant0 = new Restaurant(0L, "음식점0", 10000, 5000, 0, 0);
        Restaurant testRestaurant1 = new Restaurant(1L, "음식점1", 10000, 5000, 1, 1);
        Restaurant testRestaurant2 = new Restaurant(2L, "음식점2", 5000, 4500, 2, 2);
        Restaurant testRestaurant3 = new Restaurant(3L, "음식점3", 5000, 4500, 3, 3);
        Restaurant testRestaurant4 = new Restaurant(4L, "음식점4", 7000, 6000, 4, 4);
        testRestaurant1 = restaurantRepository.save(testRestaurant1);
        testRestaurant2 = restaurantRepository.save(testRestaurant2);
        testRestaurant3 = restaurantRepository.save(testRestaurant3);
        testRestaurant4 = restaurantRepository.save(testRestaurant4);
    }
}
