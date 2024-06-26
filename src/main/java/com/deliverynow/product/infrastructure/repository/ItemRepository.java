package com.deliverynow.product.infrastructure.repository;

import com.deliverynow.product.infrastructure.repository.entity.ItemEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepository implements PanacheMongoRepository<ItemEntity> {
}
