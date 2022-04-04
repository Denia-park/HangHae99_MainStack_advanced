package com.sparta.springadvanced_hh99homework.dto;

import com.sparta.springadvanced_hh99homework.model.EachOrderSpec;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpecFoodDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderResponseFood> foods;
    private Integer deliveryFee;
    private Long totalPrice;

    @Getter
    class OrderResponseFood {
        private String name;
        private Integer quantity;
        private Integer price;

        public OrderResponseFood(String name, Integer quantity, Integer price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }
    }

    public OrderResponseDto(EachOrderSpec savedEachOrderSpec, List<EachOrderSpecFoodDetail> eachOrderSpecFoodDetailList) {
        this.restaurantName = savedEachOrderSpec.getRestaurant().getName();
        this.deliveryFee =  savedEachOrderSpec.getDeliveryFee();
        this.totalPrice = savedEachOrderSpec.getEachOrderTotalPrice();

        List<OrderResponseFood> orderResponseFoods = new ArrayList<>();
        for (EachOrderSpecFoodDetail each : eachOrderSpecFoodDetailList) {
            String foodName= each.getFood().getName();
            Integer foodQuantity= each.getQuantity();
            Integer foodPrice= each.getFood().getPrice() * foodQuantity;
            orderResponseFoods.add(new OrderResponseFood(foodName, foodQuantity, foodPrice));
        }
        this.foods = orderResponseFoods;
    }
}
