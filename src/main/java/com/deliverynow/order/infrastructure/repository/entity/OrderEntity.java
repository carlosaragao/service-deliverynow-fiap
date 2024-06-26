package com.deliverynow.order.infrastructure.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "Order")
public class OrderEntity {

    private ClientEntity client;
    private List<ItemEntity> items;
    private OrderDetailEntity orderDetails;
    private TotalEntity total;
    private PaymentEntity payment;
}
