package com.deliverynow.product.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id;
    private String name;
    private String description;
    private CategoryEnum category;
    private Double price;
    private String image;
    private Boolean availability;
    private List<String> ingredients;
    private String breadType;
    private String dessertType;
    private String beverageType;
    private String portionSize;
    private Double weight;
    private Integer calories;
    private Integer volume;
}
