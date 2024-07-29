package com.deliverynow.product.infrastructure.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "item")
public class ItemEntity {

    private ObjectId id;
    private String productId;
    private String customerId;
    private String name;
    private String description;
    private String category;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}
