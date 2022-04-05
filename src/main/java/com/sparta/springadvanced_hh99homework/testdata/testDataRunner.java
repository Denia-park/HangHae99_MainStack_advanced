package com.sparta.springadvanced_hh99homework.testdata;

import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.model.User;
import com.sparta.springadvanced_hh99homework.model.UserRoleEnum;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import com.sparta.springadvanced_hh99homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class testDataRunner implements ApplicationRunner {

    private final RestaurantRepository restaurantRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public testDataRunner(RestaurantRepository restaurantRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 테스트 User 생성
        Restaurant testRestaurant0 = new Restaurant(0L, "음식점A", 10000, 5000, 2, 5);
        Restaurant testRestaurant1 = new Restaurant(1L, "음식점B", 10000, 5000, 3, 4);
        Restaurant testRestaurant2 = new Restaurant(2L, "음식점C", 5000, 4500, 5, 5);
        Restaurant testRestaurant3 = new Restaurant(3L, "음식점D", 5000, 4500, 2, 0);
        Restaurant testRestaurant4 = new Restaurant(4L, "음식점E", 7000, 6000, 4, 0);
        User testUser1 = new User("정국", passwordEncoder.encode("123"), UserRoleEnum.USER);
        User testUser2 = new User("제이홉", passwordEncoder.encode("123"), UserRoleEnum.USER);
        User testAdminUser1 = new User("admin", passwordEncoder.encode("123"), UserRoleEnum.STORE_OWNER);

        testRestaurant0 = restaurantRepository.save(testRestaurant0);
        testRestaurant1 = restaurantRepository.save(testRestaurant1);
        testRestaurant2 = restaurantRepository.save(testRestaurant2);
        testRestaurant3 = restaurantRepository.save(testRestaurant3);
        testRestaurant4 = restaurantRepository.save(testRestaurant4);
        testUser1 = userRepository.save(testUser1);
        testUser2 = userRepository.save(testUser2);
        testAdminUser1 = userRepository.save(testAdminUser1);
    }
}
