package com.sparta.springadvanced_hh99homework.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    //서버(1XX) : 500 Internal Server Err
    //클라이언트(2XX) : 400 Bad Request Err
        //    등록(21X)
        //      입력 (210)
        //          1. 잘못 입력(211) : 중복된 사용자 ID, 음식점 이름 입력, 음식 이름 입력, 음식 가격, 최소 주문 가격 , 기본 배달비 , 좌표
    RECHECK_REGISTER_OWNER_CODE(HttpStatus.BAD_REQUEST, "211_0", "잘못된 사장님 코드를 입력하셨습니다.."),
    RECHECK_REGISTER_USER_ID(HttpStatus.BAD_REQUEST, "211_1", "중복된 ID가 있습니다."),
    RECHECK_REGISTER_RESTAURANT_NAME(HttpStatus.BAD_REQUEST, "211_2", "잘못된 음식점 이름을 입력하셨습니다."),
    RECHECK_REGISTER_FOOD_NAME(HttpStatus.BAD_REQUEST, "211_3", "잘못된 음식 이름 및 중복된 음식 이름을 입력하셨습니다."),
    RECHECK_REGISTER_FOOD_PRICE(HttpStatus.BAD_REQUEST, "211_4", "잘못된 음식 가격 을 입력하셨습니다."),
    RECHECK_REGISTER_MIN_ORDER_PRICE(HttpStatus.BAD_REQUEST, "211_5", "최소 주문 가격을 다시 확인해주세요."),
    RECHECK_REGISTER_DELIVERY_FEE(HttpStatus.BAD_REQUEST, "211_6", "기본 배달비를 다시 확인해주세요."),
    RECHECK_REGISTER_COORDINATE(HttpStatus.BAD_REQUEST, "211_7", "좌표를 다시 한번 확인해주세요."),

    //    서비스(22X)
        //      입력 (220)
        //          1. 잘못 입력(221) : 음식점 ID , 음식 ID , 좌표 입력 , 수량 입력
    NOT_FOUND_RESTAURANT_ID(HttpStatus.NOT_FOUND, "221_1", "해당 음식점 ID는 존재하지 않습니다."),
    NOT_FOUND_FOOD_ID(HttpStatus.NOT_FOUND, "221_2", "해당 음식 ID는 존재하지 않습니다."),
    RECHECK_COORDINATE(HttpStatus.BAD_REQUEST, "221_3", "좌표를 다시 한번 확인해주세요."),
        //          2. 조건 미달(222) : 최소 주문 가격 ,최소 주문 수량
    BELOW_MIN_ORDER_PRICE(HttpStatus.BAD_REQUEST, "222_1", "음식점의 최소 주문 가격을 확인해주세요."),
    BELOW_MIN_ORDER_FOOD_NUM(HttpStatus.BAD_REQUEST, "222_2", "음식점의 최소 주문 수량을 확인해주세요.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
