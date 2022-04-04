package com.sparta.springadvanced_hh99homework.repository;

import com.sparta.springadvanced_hh99homework.model.EachOrderSpec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EachOrderRepository extends JpaRepository<EachOrderSpec,Long> {
}
