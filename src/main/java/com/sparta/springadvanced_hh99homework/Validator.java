package com.sparta.springadvanced_hh99homework;

import com.sparta.springadvanced_hh99homework.exception.ErrorCode;
import com.sparta.springadvanced_hh99homework.exception.HGPrivateException;
import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpecFoodDetail;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validator {

    public void validateInput(Restaurant receivedRestaurant) {
        if (!isValidName(receivedRestaurant.getName())) {
            throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_RESTAURANT_NAME);
        }

        if (!isValidMinOrderPrice(receivedRestaurant.getMinOrderPrice())) {
            throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_MIN_ORDER_PRICE);
        }

        if (!isValidDeliveryFee(receivedRestaurant.getDeliveryFee())) {
            throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_DELIVERY_FEE);
        }

        if (!isValidCordinate(receivedRestaurant.getX(), receivedRestaurant.getY())) {
            throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_COORDINATE);
       }
    }

    private boolean isValidCordinate(Integer x, Integer y) {
        if (x == null || y == null) {
            throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_COORDINATE);
        }
        // X : 0~99 , Y : 0~99 허용
        boolean validX = (0 <= x && x <= 99);
        boolean validY = (0 <= y && y <= 99);
        return validX && validY;
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
            throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_FOOD_NAME);
        }

        if (!isValidPrice(receivedFood.getPrice())) {
            throw new HGPrivateException(ErrorCode.RECHECK_REGISTER_FOOD_PRICE);
        }
    }

    public void validateInput(List<EachOrderSpecFoodDetail> orderRequest) {
        for (EachOrderSpecFoodDetail foodRequestDto : orderRequest) {
            if(!isValidQuantity(foodRequestDto.getQuantity()))
                throw new HGPrivateException(ErrorCode.BELOW_MIN_ORDER_FOOD_NUM);
        }
    }

    private boolean isValidQuantity(Integer quantity) {
        // 1. 허용값: 1 ~ 100
        return 1 <= quantity && quantity <= 100;
    }
}
