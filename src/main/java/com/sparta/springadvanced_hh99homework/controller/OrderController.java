package com.sparta.springadvanced_hh99homework.controller;

import com.sparta.springadvanced_hh99homework.dto.FoodRequestDto;
import com.sparta.springadvanced_hh99homework.dto.OrderRequestDto;
import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.OrderRequest;
import com.sparta.springadvanced_hh99homework.model.OrderedFoodDetail;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.FoodRepository;
import com.sparta.springadvanced_hh99homework.repository.OrderedFoodDetailRepository;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import com.sparta.springadvanced_hh99homework.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private RestaurantRepository restaurantRepository;
    private FoodRepository foodRepository;
    private OrderFoodService orderFoodService;

    private OrderedFoodDetailRepository orderedFoodDetailRepository;

    @Autowired
    public OrderController(RestaurantRepository restaurantRepository, FoodRepository foodRepository, OrderFoodService orderFoodService, OrderedFoodDetailRepository orderedFoodDetailRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.orderFoodService = orderFoodService;
        this.orderedFoodDetailRepository = orderedFoodDetailRepository;
    }

    @PostMapping("/order/request")
    public void orderRequest(@RequestBody OrderRequestDto orderRequestDto){
        OrderRequest a = orderFoodService.orderRequest(convertDtoToModel(orderRequestDto));
        System.out.println("a");
    }

//    @GetMapping("/orders")
//    public List<OrderResponseDto> getOrders(){
//        return convertModelsToDtos(orderFoodService.getOrders());
//    }

//    private List<OrderResponseDto> convertModelsToDtos(List<OrderRequest> orderRequestList) {
//    }

    public OrderRequest convertDtoToModel(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(()-> new IllegalArgumentException("해당 음식점 ID는 존재하지 않습니다."));

        List<OrderedFoodDetail> orderedFoodDetailList = new ArrayList<OrderedFoodDetail>();
        for (FoodRequestDto foodRequestDto : orderRequestDto.getFoods()) {
            Food findedFood = foodRepository.findByRestaurantAndFoodId(restaurant, foodRequestDto.getId())
                    .orElseThrow(()-> new IllegalArgumentException("해당 음식이 존재하지 않습니다."));

            OrderedFoodDetail orderedFoodDetail = new OrderedFoodDetail(findedFood, foodRequestDto.getQuantity());

            OrderedFoodDetail savedOrderedFoodDetail = orderedFoodDetailRepository.save(orderedFoodDetail);

            orderedFoodDetailList.add(savedOrderedFoodDetail);
        }

        return new OrderRequest(restaurant , orderedFoodDetailList);
    }
}
