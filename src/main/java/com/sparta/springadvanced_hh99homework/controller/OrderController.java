package com.sparta.springadvanced_hh99homework.controller;

import com.sparta.springadvanced_hh99homework.dto.FoodRequestDto;
import com.sparta.springadvanced_hh99homework.dto.OrderRequestDto;
import com.sparta.springadvanced_hh99homework.dto.OrderResponseDto;
import com.sparta.springadvanced_hh99homework.exception.ErrorCode;
import com.sparta.springadvanced_hh99homework.exception.HGPrivateException;
import com.sparta.springadvanced_hh99homework.model.Food;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpec;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpecFoodDetail;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import com.sparta.springadvanced_hh99homework.repository.EachOrderRepository;
import com.sparta.springadvanced_hh99homework.repository.FoodRepository;
import com.sparta.springadvanced_hh99homework.repository.OrderedFoodDetailRepository;
import com.sparta.springadvanced_hh99homework.repository.RestaurantRepository;
import com.sparta.springadvanced_hh99homework.service.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private EachOrderRepository eachOrderRepository;

    @Autowired
    public OrderController(RestaurantRepository restaurantRepository, FoodRepository foodRepository, OrderFoodService orderFoodService, OrderedFoodDetailRepository orderedFoodDetailRepository, EachOrderRepository eachOrderRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.orderFoodService = orderFoodService;
        this.orderedFoodDetailRepository = orderedFoodDetailRepository;
        this.eachOrderRepository = eachOrderRepository;
    }

    @PostMapping("/order/request")
    public OrderResponseDto orderRequest(@RequestBody OrderRequestDto orderRequestDto){
        Restaurant findRestaurant = findRestaurant(orderRequestDto);
        EachOrderSpec eachOrderSpec = new EachOrderSpec(findRestaurant);

        List<EachOrderSpecFoodDetail> eachOrderSpecFoodDetailList = convertDtoToModel(findRestaurant,orderRequestDto.getFoods());
        EachOrderSpec savedEachOrderSpec = orderFoodService.orderRequest(eachOrderSpec, eachOrderSpecFoodDetailList);

        return convertModelsToDto(savedEachOrderSpec);
    }

    private OrderResponseDto convertModelsToDto(EachOrderSpec savedEachOrderSpec) {
        List<EachOrderSpecFoodDetail> savedEachOrderSpecFoodDetailList = orderedFoodDetailRepository.findAllByEachOrderSpec(savedEachOrderSpec);

        return new OrderResponseDto(savedEachOrderSpec,savedEachOrderSpecFoodDetailList);
    }

    private List<OrderResponseDto> convertModelsToDto(List<EachOrderSpec> savedEachOrderSpeces) {
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (EachOrderSpec savedEachOrderSpec : savedEachOrderSpeces) {
            List<EachOrderSpecFoodDetail> savedEachOrderSpecFoodDetailList = orderedFoodDetailRepository.findAllByEachOrderSpec(savedEachOrderSpec);
            orderResponseDtoList.add(new OrderResponseDto(savedEachOrderSpec, savedEachOrderSpecFoodDetailList));
        }

        return orderResponseDtoList;
    }

    private Restaurant findRestaurant(OrderRequestDto orderRequestDto) {
        return restaurantRepository.findByRestaurantId(orderRequestDto.getRestaurantId())
                .orElseThrow(()-> new HGPrivateException(ErrorCode.NOT_FOUND_RESTAURANT_ID));
    }

    private List<EachOrderSpecFoodDetail> convertDtoToModel(Restaurant findRestaurant, List<FoodRequestDto> foods) {
        List<EachOrderSpecFoodDetail> eachOrderSpecFoodDetailList = new ArrayList<EachOrderSpecFoodDetail>();

        for (FoodRequestDto foodRequestDto : foods) {
            Food findedFood = foodRepository.findByRestaurantAndFoodId(findRestaurant, foodRequestDto.getId())
                    .orElseThrow(()-> new HGPrivateException(ErrorCode.NOT_FOUND_FOOD_ID));
            eachOrderSpecFoodDetailList.add(new EachOrderSpecFoodDetail(findedFood, foodRequestDto));
        }

        return eachOrderSpecFoodDetailList;
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrders(){
        List<EachOrderSpec> savedEachOrderSpec = eachOrderRepository.findAll();

        return convertModelsToDto(savedEachOrderSpec);
    }
}
