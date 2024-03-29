package com.sparta.springadvanced_hh99homework;

import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpecFoodDetail;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validator {
    private final String RESTAURANT_HANGUL = "음식점 : ";
    private final String FOOD_HANGUL = "음식 : ";

    public void validateInput(Restaurant receivedRestaurant) {
        if (!isValidName(receivedRestaurant.getName())) {
            throw new IllegalReceiveException(RESTAURANT_HANGUL + "이름을 입력해주세요");
        }

        if (!isValidMinOrderPrice(receivedRestaurant.getMinOrderPrice())) {
            throw new IllegalReceiveException(RESTAURANT_HANGUL + "최소주문 가격 에러");
        }

        if (!isValidDeliveryFee(receivedRestaurant.getDeliveryFee())) {
            throw new IllegalReceiveException(RESTAURANT_HANGUL + "기본 배달비 에러");
        }
    }

    private boolean isValidName(String name) {
        return (name != null && name.trim().length() != 0);
    }

    private boolean isValidMinOrderPrice(Integer minOrderPrice) {
        //1. 허용값: 1,000원 ~ 100,000원 입력
        //2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
        return 1_000 <= minOrderPrice && minOrderPrice <= 100_000 && minOrderPrice % 100 == 0;
    }

    private boolean isValidDeliveryFee(Integer deliveryFee) {
        // 1. 허용값: 0 ~ 10,000원 입력
        // 2. 500 원 단위로만 입력 가능 (예. 2,200원 입력 시 에러발생. 2,500원 입력 가능)
        return 0 <= deliveryFee && deliveryFee <= 10_000 && deliveryFee % 500 == 0;
    }

    private boolean isValidPrice(Integer price) {
        // 1. 허용값: 100원 ~ 1,000,000원
        // 2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원 입력 가능)
        return 100 <= price && price <= 1_000_000 && price % 100 == 0;
    }

    public void validateInput(Food receivedFood) {
        if (!isValidName(receivedFood.getName())) {
            throw new IllegalReceiveException(FOOD_HANGUL + "이름을 입력해주세요");
        }

        if (!isValidPrice(receivedFood.getPrice())) {
            throw new IllegalReceiveException(FOOD_HANGUL + "가격 에러");
        }
    }

    public void validateInput(List<EachOrderSpecFoodDetail> orderRequest) {
        for (EachOrderSpecFoodDetail foodRequestDto : orderRequest) {
            if(!isValidQuantity(foodRequestDto.getQuantity()))
                throw new IllegalReceiveException(FOOD_HANGUL + "최소 주문 수량을 맞춰주세요.");
        }
    }

    private boolean isValidQuantity(Integer quantity) {
        // 1. 허용값: 1 ~ 100
        return 1 <= quantity && quantity <= 100;
    }
}
