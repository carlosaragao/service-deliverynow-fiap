package com.deliverynow.product.adapters.controller.response;


import java.util.List;

public record ProductResponse(
        String id,
        String name,
        String description,
        String category,
        Double price,
        String image,
        Boolean availability,
        List<String> ingredients,
        String breadType,
        String dessertType,
        String beverageType,
        String portionSize,
        double weight,
        int calories,
        int volume
) {
}
