package com.deliverynow.order.infrastructure.repository;


import com.deliverynow.order.infrastructure.repository.entity.OrderEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<OrderEntity> {
}
