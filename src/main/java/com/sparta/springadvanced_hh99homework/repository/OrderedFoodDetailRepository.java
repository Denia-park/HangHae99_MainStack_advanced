package com.sparta.springadvanced_hh99homework.repository;

import com.sparta.springadvanced_hh99homework.model.EachOrderSpecFoodDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedFoodDetailRepository extends JpaRepository<EachOrderSpecFoodDetail,Long> {
}
