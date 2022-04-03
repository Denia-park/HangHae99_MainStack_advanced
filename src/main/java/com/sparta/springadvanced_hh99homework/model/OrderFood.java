package com.sparta.springadvanced_hh99homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderFood {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
