package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.Validator;
import com.sparta.springadvanced_hh99homework.model.EachOrder;
import com.sparta.springadvanced_hh99homework.model.OrderedFoodDetail;
import com.sparta.springadvanced_hh99homework.repository.EachOrderRepository;
import com.sparta.springadvanced_hh99homework.repository.OrderedFoodDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderFoodService {
    private final Validator validator;
    private EachOrderRepository eachOrderRepository;
    private OrderedFoodDetailRepository orderedFoodDetailRepository;

    @Autowired
    public OrderFoodService(Validator validator, EachOrderRepository eachOrderRepository, OrderedFoodDetailRepository orderedFoodDetailRepository) {
        this.validator = validator;
        this.eachOrderRepository = eachOrderRepository;
        this.orderedFoodDetailRepository = orderedFoodDetailRepository;
    }

    @Transactional
    public EachOrder orderRequest(EachOrder eachOrder, List<OrderedFoodDetail> foods) {
        validator.validateInput(foods);

        EachOrder savedEachOrder = saveEachOrder(eachOrder);
        
        saveOrderedFoodDetailList(savedEachOrder,foods);

//        eachOrder.setOrderRequestTotalPrice(calculateOrderRequestTotalPrice(eachOrder));
//
//        eachOrder.setDeliveryFee(calculateDeliveryFee(eachOrder));
//
//        eachOrder.setOrderRequestId(OrderRequestIdCounter);

        return null;
    }

    private EachOrder saveEachOrder(EachOrder eachOrder) {
        long OrderRequestIdCounter = eachOrderRepository.findAll().size() + 1;
        eachOrder.setOrderRequestId(OrderRequestIdCounter);
        return eachOrderRepository.save(eachOrder);
    }

    private void saveOrderedFoodDetailList(EachOrder savedEachOrder,List<OrderedFoodDetail> foods) {
        for (OrderedFoodDetail food : foods) {
            food.setEachOrder(savedEachOrder);

            orderedFoodDetailRepository.save(food);
        }
    }

    private Long calculateOrderRequestTotalPrice(EachOrder eachOrder) {
//        Long orderRequestTotalPrice = 0L;
//
//        for (OrderedFoodDetail orderedFoodDetail : eachOrder.getOrderedFoodDetailList()) {
//            orderedFoodDetail.setOrderedFoodTotalPrice(calculateOrderFoodTotalPrice(orderedFoodDetail));
//            orderRequestTotalPrice += orderedFoodDetail.getOrderedFoodTotalPrice();
//        }
//
//        if(orderRequestTotalPrice + eachOrder.getDeliveryFee() < eachOrder.getRestaurant().getMinOrderPrice())
//            throw new IllegalIdentifierException("최소 주문 가격을 넘지 못해서 주문이 불가합니다.");
//
//        return orderRequestTotalPrice + eachOrder.getDeliveryFee();
        return null;
    }

    private Long calculateOrderFoodTotalPrice(OrderedFoodDetail orderedFoodDetail) {
        return (long) orderedFoodDetail.getQuantity() * orderedFoodDetail.getFood().getPrice();
    }

    private Integer calculateDeliveryFee(EachOrder eachOrder) {
        return eachOrder.getRestaurant().getDeliveryFee();
    }

//    public Object getOrders() {
//
//    }

}
