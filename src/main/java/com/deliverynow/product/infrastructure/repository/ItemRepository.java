package com.deliverynow.product.infrastructure.repository;

import com.deliverynow.product.infrastructure.repository.entity.ItemEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ItemRepository implements PanacheMongoRepository<ItemEntity> {

    public List<ItemEntity> getItemByCustomer(String customerId){
        return find("customerId", customerId).list();
    }
}
