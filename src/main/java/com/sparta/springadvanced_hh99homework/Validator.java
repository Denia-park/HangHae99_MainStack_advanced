package com.sparta.springadvanced_hh99homework;

import com.sparta.springadvanced_hh99homework.dto.RestaurantRequestDto;
import com.sun.nio.sctp.IllegalReceiveException;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public void validateRestaurantInput(RestaurantRequestDto requestDto) {
        if (isNotValidName(requestDto.getName())) {
            throw new IllegalReceiveException("이름을 입력해주세요");
        }

        if (isNotValidMinOrderPrice(requestDto.getMinOrderPrice())) {
            throw new IllegalReceiveException("최소주문 가격 에러");
        }

        if (isNotValidDeliveryFee(requestDto.getDeliveryFee())) {
            throw new IllegalReceiveException("기본 배달비 에러");
        }
    }

    private boolean isNotValidName(String name) {
        return (name == null || name.trim().length() == 0);
    }

    private boolean isNotValidMinOrderPrice(Integer minOrderPrice) {
        // 허용값: 1,000원 ~ 100,000원 입력
        if(minOrderPrice < 1_000 || minOrderPrice > 100_000)
            return true;

        // 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
        if(minOrderPrice % 100 != 0)
            return true;

        return false;
    }

    private boolean isNotValidDeliveryFee(Integer deliveryFee) {
        // 허용값: 1,000원 ~ 100,000원 입력
        if(deliveryFee < 0 || deliveryFee > 10_000)
            return true;

        // 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
        if(deliveryFee % 500 != 0)
            return true;

        return false;
    }
}
