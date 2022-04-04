package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.Validator;
import com.sparta.springadvanced_hh99homework.dto.OrderResponseDto;
import com.sparta.springadvanced_hh99homework.model.OrderRequest;
import com.sparta.springadvanced_hh99homework.model.OrderedFoodDetail;
import com.sparta.springadvanced_hh99homework.repository.FoodRepository;
import com.sparta.springadvanced_hh99homework.repository.OrderRequestRepository;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFoodService {
    private final Validator validator;
    private OrderRequestRepository orderRequestRepository;

    @Autowired
    public OrderFoodService(Validator validator, OrderRequestRepository orderRequestRepository) {
        this.validator = validator;
        this.orderRequestRepository = orderRequestRepository;
    }

    public OrderRequest orderRequest(OrderRequest orderRequest) {
        validator.validateInput(orderRequest);
        orderRequest.setDeliveryFee(calculateDeliveryFee(orderRequest));
        orderRequest.setOrderRequestTotalPrice(calculateOrderRequestTotalPrice(orderRequest));

        long OrderRequestIdCounter = orderRequestRepository.findAll().size() + 1;
        orderRequest.setOrderRequestId(OrderRequestIdCounter);

        return orderRequestRepository.save(orderRequest);
    }

    private Long calculateOrderRequestTotalPrice(OrderRequest orderRequest) {
        Long orderRequestTotalPrice = 0L;

        for (OrderedFoodDetail orderedFoodDetail : orderRequest.getOrderedFoodDetailList()) {
            orderedFoodDetail.setOrderedFoodTotalPrice(calculateOrderFoodTotalPrice(orderedFoodDetail));
            orderRequestTotalPrice += orderedFoodDetail.getOrderedFoodTotalPrice();
        }

        if(orderRequestTotalPrice + orderRequest.getDeliveryFee() < orderRequest.getRestaurant().getMinOrderPrice())
            throw new IllegalIdentifierException("최소 주문 가격을 넘지 못해서 주문이 불가합니다.");

        return orderRequestTotalPrice + orderRequest.getDeliveryFee();
    }

    private Long calculateOrderFoodTotalPrice(OrderedFoodDetail orderedFoodDetail) {
        return (long) orderedFoodDetail.getQuantity() * orderedFoodDetail.getFood().getPrice();
    }

    private Integer calculateDeliveryFee(OrderRequest orderRequest) {
        return orderRequest.getRestaurant().getDeliveryFee();
    }

//    public Object getOrders() {
//
//    }

}
