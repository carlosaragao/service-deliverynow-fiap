package com.deliverynow.product.infrastructure.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "product")
public class ProductEntity {

    private ObjectId id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private String image;
    private Boolean availability;
    private LocalDateTime registrationDate;
    private List<String> ingredients;
    private String breadType;
    private String dessertType;
    private String beverageType;
    private String portionSize;
    private double weight;
    private int calories;
    private int volume;
}