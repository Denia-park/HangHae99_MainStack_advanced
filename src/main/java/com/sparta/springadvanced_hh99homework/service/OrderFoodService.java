package com.sparta.springadvanced_hh99homework.service;

import com.sparta.springadvanced_hh99homework.Validator;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpec;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpecFoodDetail;
import com.sparta.springadvanced_hh99homework.repository.EachOrderRepository;
import com.sparta.springadvanced_hh99homework.repository.OrderedFoodDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public EachOrderSpec orderRequest(EachOrderSpec eachOrderSpec, List<EachOrderSpecFoodDetail> foods) {
        validator.validateInput(foods);

        EachOrderSpec savedEachOrderSpec = saveEachOrder(eachOrderSpec);

        List<EachOrderSpecFoodDetail> savedFoods = saveOrderedFoodDetailList(savedEachOrderSpec,foods);

        Long totalFoodPrice = getTotalFoodPrice(savedFoods);

        if(totalFoodPrice < eachOrderSpec.getRestaurant().getMinOrderPrice())
            throw new IllegalArgumentException("음식점의 최소 주문 가격을 넘겨야합니다.");

        Integer eachOrderDeliveryFee = getEachOrderDeliveryFee(eachOrderSpec,totalFoodPrice);
        eachOrderSpec.setDeliveryFee(eachOrderDeliveryFee);

        eachOrderSpec.setEachOrderTotalPrice( (long) totalFoodPrice + eachOrderDeliveryFee);

        return eachOrderSpec;
    }

    private Integer getEachOrderDeliveryFee(EachOrderSpec eachOrderSpec, Long totalFoodPrice) {
        //주문한 음식 가격에 따라 배달비가 달라짐.
        //여기서는 따로 조건이 없으므로 생략
        return eachOrderSpec.getRestaurant().getDeliveryFee();
    }

    private Long getTotalFoodPrice(List<EachOrderSpecFoodDetail> savedFoods) {
        Long totalFoodPrice = 0L;

        for (EachOrderSpecFoodDetail savedfFood : savedFoods) {
            totalFoodPrice += savedfFood.getOrderedEachFoodTotalPrice();
        }

        return totalFoodPrice;
    }

    private EachOrderSpec saveEachOrder(EachOrderSpec eachOrderSpec) {
        long OrderRequestIdCounter = eachOrderRepository.findAll().size() + 1;
        eachOrderSpec.setEachOrderId(OrderRequestIdCounter);
        return eachOrderRepository.save(eachOrderSpec);
    }

    private List<EachOrderSpecFoodDetail> saveOrderedFoodDetailList(EachOrderSpec savedEachOrderSpec, List<EachOrderSpecFoodDetail> foods) {
        List<EachOrderSpecFoodDetail> savedEachOrderSpecFoodDetailList = new ArrayList<EachOrderSpecFoodDetail>();
        for (EachOrderSpecFoodDetail food : foods) {
            food.setEachOrderSpec(savedEachOrderSpec);
            food.setOrderedEachFoodTotalPrice( (long) food.getQuantity() * food.getFood().getPrice());

            savedEachOrderSpecFoodDetailList.add(orderedFoodDetailRepository.save(food));
        }
        return savedEachOrderSpecFoodDetailList;
    }

    private Long calculateOrderRequestTotalPrice(EachOrderSpec eachOrderSpec) {
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

    private Long calculateOrderFoodTotalPrice(EachOrderSpecFoodDetail eachOrderSpecFoodDetail) {
        return (long) eachOrderSpecFoodDetail.getQuantity() * eachOrderSpecFoodDetail.getFood().getPrice();
    }

    private Integer calculateDeliveryFee(EachOrderSpec eachOrderSpec) {
        return eachOrderSpec.getRestaurant().getDeliveryFee();
    }

//    public Object getOrders() {
//
//    }

}
