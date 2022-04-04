package com.sparta.springadvanced_hh99homework.controller;

import com.sparta.springadvanced_hh99homework.dto.FoodRequestDto;
import com.sparta.springadvanced_hh99homework.dto.OrderRequestDto;
import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpec;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpecFoodDetail;
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
        Restaurant findRestaurant = findRestaurant(orderRequestDto);
        EachOrderSpec eachOrderSpec = new EachOrderSpec(findRestaurant);

        List<EachOrderSpecFoodDetail> eachOrderSpecFoodDetailList = convertDtoToModel(findRestaurant,orderRequestDto.getFoods());
        EachOrderSpec a = orderFoodService.orderRequest(eachOrderSpec, eachOrderSpecFoodDetailList);
        System.out.println("a");
    }

    private Restaurant findRestaurant(OrderRequestDto orderRequestDto) {
        return restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(()-> new IllegalArgumentException("해당 음식점 ID는 존재하지 않습니다."));
    }

    private List<EachOrderSpecFoodDetail> convertDtoToModel(Restaurant findRestaurant, List<FoodRequestDto> foods) {
        List<EachOrderSpecFoodDetail> eachOrderSpecFoodDetailList = new ArrayList<EachOrderSpecFoodDetail>();

        for (FoodRequestDto foodRequestDto : foods) {
            Food findedFood = foodRepository.findByRestaurantAndFoodId(findRestaurant, foodRequestDto.getId())
                    .orElseThrow(()-> new IllegalArgumentException("해당 하는 음식은 존재하지 않습니다."));
            eachOrderSpecFoodDetailList.add(new EachOrderSpecFoodDetail(findedFood, foodRequestDto));
        }

        return eachOrderSpecFoodDetailList;
    }

//    @GetMapping("/orders")
//    public List<OrderResponseDto> getOrders(){
//        return convertModelsToDtos(orderFoodService.getOrders());
//    }

//    private List<OrderResponseDto> convertModelsToDtos(List<OrderRequest> orderRequestList) {
//    }
}
