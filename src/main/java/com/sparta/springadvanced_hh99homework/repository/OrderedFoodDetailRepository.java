package com.sparta.springadvanced_hh99homework.repository;

import com.sparta.springadvanced_hh99homework.model.EachOrderSpec;
import com.sparta.springadvanced_hh99homework.model.EachOrderSpecFoodDetail;
import com.sparta.springadvanced_hh99homework.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedFoodDetailRepository extends JpaRepository<EachOrderSpecFoodDetail,Long> {
    List<EachOrderSpecFoodDetail> findAllByEachOrderSpec(EachOrderSpec eachOrderSpec);
}
